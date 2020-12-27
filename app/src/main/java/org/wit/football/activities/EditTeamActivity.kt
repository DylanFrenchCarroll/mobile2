package org.wit.football.activities

import TeamJsonStore
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_edit_team_layout.*
import kotlinx.android.synthetic.main.activity_fantasy.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.football.R
import org.wit.football.models.PlayerModel
import org.wit.placemark.app.models.TeamModel
import java.io.Serializable
import java.util.ArrayList

class EditTeamActivity : AppCompatActivity(), AnkoLogger, Serializable {

    lateinit var TeamOperations: TeamJsonStore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_team_layout)
        var context: Context = getApplicationContext();
        TeamOperations = TeamJsonStore(context)
        var team: TeamModel;

        val switchCase: String = intent.getSerializableExtra("case") as String
        info(switchCase)



            val intentTeam: TeamModel = intent.getSerializableExtra("myTeam") as TeamModel
            team = intentTeam
            var textViewTeamName = findViewById(R.id.editTeamName) as TextView
            if (intentTeam != null) {
                textViewTeamName.setText(intentTeam.name)
            }



        if (switchCase == "PlayerList") {
            var updatedPlayers =
                intent.getSerializableExtra("myUpdatedPlayerList") as ArrayList<PlayerModel>
            if (updatedPlayers != null) {
                info("Updating Team'##################################")
                TeamOperations.teamUpdatePlayers(intentTeam, updatedPlayers)
            }
        }


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