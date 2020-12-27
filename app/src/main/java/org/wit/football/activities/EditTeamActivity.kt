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
import org.wit.placemark.app.models.TeamModel

class EditTeamActivity : AppCompatActivity(), AnkoLogger {

    lateinit var TeamOperations: TeamJsonStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_team_layout)
        var context: Context = getApplicationContext();
        TeamOperations = TeamJsonStore(context)

        //Getting Team from intent
        val intentTeam: TeamModel = intent.getSerializableExtra("myTeam") as TeamModel
        var  textViewTeamName = findViewById(R.id.editTeamName) as TextView
        if (intentTeam != null) {
            textViewTeamName.setText(intentTeam.name)
        }

        btn_editTeamName.setOnClickListener() {
            info("Change Name Pressed:")
            TeamOperations.teamUpdateName(intentTeam, textViewTeamName.text.toString() )
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

        btn_editGoBack.setOnClickListener(){
            info("Go Back")
            var i = Intent(context, ListTeamsActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i)
        }

    }



}