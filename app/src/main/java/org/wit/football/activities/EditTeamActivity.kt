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
    lateinit var team: TeamModel;
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_team_layout)
        context = getApplicationContext();
        TeamOperations = TeamJsonStore(context)

        var PlayerOperations = PlayerJsonStore()
        var playersList = PlayerOperations.playerFindAll()
        val switchCase: String = intent.getSerializableExtra("case") as String
        team = intent.getSerializableExtra("myTeam") as TeamModel
        var textViewTeamName = findViewById(R.id.editTeamName) as TextView

        if (team != null) {
            textViewTeamName.setText(team.name)
        }

        if (switchCase == "PlayerList") {
            var updatedPlayers =
                intent.getSerializableExtra("myUpdatedPlayerList") as ArrayList<PlayerModel>
            if (updatedPlayers != null) {
                TeamOperations.teamUpdatePlayers(team, updatedPlayers)
                team.players = updatedPlayers
            }
        }

        var recyclerView = findViewById(R.id.squadPlayerRecyclerView) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val thisAdapter = PlayerAdapter(this, team.players, team)
        recyclerView.adapter = thisAdapter


        btn_editTeamName.setOnClickListener() {
            TeamOperations.teamUpdateName(team, textViewTeamName.text.toString())
        }

        btn_deleteSquad.setOnClickListener() {
            TeamOperations.teamDelete(team)
            var i = Intent(context, ListTeamsActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i)
        }

        btn_editSquad.setOnClickListener() {
            var i = Intent(context, ListPlayersActivity::class.java)
            i.putExtra("myTeam", team)
            i.putExtra("playerList", playersList)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i)
        }

        btn_editGoBack.setOnClickListener() {
            var i = Intent(context, ListTeamsActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i)
        }
    }
}