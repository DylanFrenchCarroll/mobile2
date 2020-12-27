package org.wit.football.activities

import TeamJsonStore
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.AnkoLogger
import org.wit.football.R
import org.wit.football.adapters.TeamAdapter
import org.wit.football.models.PlayerModel
import org.wit.placemark.app.models.TeamModel

class ListTeamsActivity : AppCompatActivity(), AnkoLogger {

    lateinit var teamList: List<TeamModel>
    lateinit var context: Context
    lateinit var TeamOperations: TeamJsonStore
    lateinit var app: FantasyFootballActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_teams)

        context = getApplicationContext();
        TeamOperations = TeamJsonStore(context)
        teamList = TeamOperations.teamFindAll()

        var recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        if(teamList.size ==0 ){
            var emptyPlayerList: ArrayList<PlayerModel> = ArrayList()
            val emptyTeam = TeamModel("No Squads Available(Please dont click me, I'm a bug)", emptyPlayerList)
            val empty = mutableListOf<TeamModel>()
            empty.add(emptyTeam)
            val adapter = TeamAdapter(this, empty  )
            recyclerView.adapter = adapter
        }else{
            val adapter = TeamAdapter(this, teamList)
            recyclerView.adapter = adapter
        }





    }




}