package org.wit.football.activities

import TeamJsonStore
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_edit_team_layout.*
import kotlinx.android.synthetic.main.activity_list_teams.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.football.R
import org.wit.football.adapters.TeamAdapter
import org.wit.football.models.PlayerModel
import org.wit.placemark.app.models.TeamModel
import java.io.Serializable


class ListTeamsActivity : AppCompatActivity(), Serializable, AnkoLogger {

    lateinit var teamList: List<TeamModel>
    lateinit var context: Context
    lateinit var TeamOperations: TeamJsonStore
    lateinit var app: FantasyFootballActivity
    lateinit var adapter: TeamAdapter
    lateinit var filteredList: List<TeamModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_teams)
        context = getApplicationContext();
        TeamOperations = TeamJsonStore(context)
        teamList = TeamOperations.teamFindAll()
        filteredList = teamList
        var recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)


        var editsearch = findViewById(R.id.search) as SearchView
        editsearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filteredList = adapter.runFilter(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                var text: String = newText;
                if (text.length == 0) {
                    filteredList = teamList
                } else {
                    filteredList = adapter.runFilter(text)
                }
                adapter = TeamAdapter(context, filteredList)
                recyclerView.adapter = adapter
                return false;
            }
        })

        if (teamList.size == 0) {
            val emptyTeam = TeamModel(
                0,
                "No Squads Available(Please dont click me, I'm a bug)",
                ArrayList<PlayerModel>()
            )
            val empty = mutableListOf<TeamModel>()
            empty.add(emptyTeam)
            adapter = TeamAdapter(this, empty)
            recyclerView.adapter = adapter
        } else {
            adapter = TeamAdapter(this, filteredList)
            recyclerView.adapter = adapter
        }


        btn_listGoBack.setOnClickListener() {
            var i = Intent(context, FantasyFootballActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i)
        }


    }

}




