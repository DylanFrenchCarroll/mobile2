package org.wit.football.activities

import TeamJsonStore
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_edit_team_layout.*
import kotlinx.android.synthetic.main.activity_fantasy.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.football.R
import org.wit.football.adapters.PlayerAdapter
import org.wit.football.models.PlayerModel
import org.wit.placemark.app.models.TeamModel
import java.io.Serializable
import java.util.ArrayList

class EditTeamActivity : AppCompatActivity(), AnkoLogger, Serializable {

    lateinit var TeamOperations: TeamJsonStore
    lateinit var playerList: List<PlayerModel>
    lateinit var team: TeamModel;
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_team_layout)
        context = getApplicationContext();
        TeamOperations = TeamJsonStore(context)

        //Receiving team and reading case to decide what to do
        val switchCase: String = intent.getSerializableExtra("case") as String
        val intentTeam: TeamModel = intent.getSerializableExtra("myTeam") as TeamModel
        team = intentTeam
        var textViewTeamName = findViewById(R.id.editTeamName) as TextView

        //Setting Name on Screen
        if (intentTeam != null) {
            textViewTeamName.setText(intentTeam.name)
        }

        //If coming from squad selection
        if (switchCase == "PlayerList") {
            var updatedPlayers = intent.getSerializableExtra("myUpdatedPlayerList") as ArrayList<PlayerModel>
            if (updatedPlayers != null) {
                TeamOperations.teamUpdatePlayers(intentTeam, updatedPlayers)
                team.players = updatedPlayers
            }
        }

        //Set the player list to show
        var recyclerView = findViewById(R.id.squadPlayerRecyclerView) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Using the adapter to display the list of players from the squad
        val thisAdapter = PlayerAdapter(this, team.players, team)
        recyclerView.adapter = thisAdapter




        btn_editTeamName.setOnClickListener() {
            info("Change Name Pressed:")
            TeamOperations.teamUpdateName(intentTeam, textViewTeamName.text.toString())
        }

        btn_deleteSquad.setOnClickListener() {
            info("Delete Button Pressed:")
            if (intentTeam != null) {
                TeamOperations.teamDelete(intentTeam)
                var i = Intent(context, ListTeamsActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i)
            }
        }

        btn_editSquad.setOnClickListener() {
            info("Change Squad!")
            var team: TeamModel = intentTeam
            var i = Intent(context, ListPlayersActivity::class.java)
            i.putExtra("myTeam", team)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i)
        }

        btn_editGoBack.setOnClickListener() {
            info("Go Back")
            var i = Intent(context, ListTeamsActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i)
        }

    }


}