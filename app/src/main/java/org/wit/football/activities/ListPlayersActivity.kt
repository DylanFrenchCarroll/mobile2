package org.wit.football.activities

import PlayerJsonStore
import TeamJsonStore
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_edit_team_layout.*
import kotlinx.android.synthetic.main.activity_list_players.*
import kotlinx.android.synthetic.main.activity_list_teams.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.football.R
import org.wit.football.adapters.PlayerAdapter
import org.wit.football.adapters.TeamAdapter
import org.wit.football.models.PlayerModel
import org.wit.placemark.app.models.TeamModel
import java.io.Serializable

class ListPlayersActivity : AppCompatActivity(), Serializable, AnkoLogger {

    lateinit var playerList: List<PlayerModel>
    lateinit var context: Context
    lateinit var PlayerOperations: PlayerJsonStore
    lateinit var app: FantasyFootballActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_players)
        context = getApplicationContext();
        PlayerOperations = PlayerJsonStore(context)
        playerList = PlayerOperations.playerFindAll()
        var recyclerView = findViewById(R.id.playerRecyclerView) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Getting Team from intent
        val intentTeam2: TeamModel = intent.getSerializableExtra("myTeam") as TeamModel
        var team: TeamModel = intentTeam2

        val adapter = PlayerAdapter(this, playerList, team)
        recyclerView.adapter = adapter






        btn_playersGoBack.setOnClickListener(){
            info("Confirmed Team Selection")
            var teamselection = adapter.getNewSquad()

            var i = Intent(context, EditTeamActivity::class.java)

            val case: String = "PlayerList"
            i.putExtra("case", case)
            i.putExtra("myUpdatedPlayerList", teamselection )
            i.putExtra("myTeam", team)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i)
        }

    }


}