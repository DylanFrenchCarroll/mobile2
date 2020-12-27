package org.wit.football.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.football.R
import org.wit.placemark.app.models.TeamModel

class EditTeamActivity : AppCompatActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_team_layout)
        val team: TeamModel? = intent.getSerializableExtra("myTeam") as TeamModel?
        var  textViewTeamName = findViewById(R.id.editTeamName) as TextView
        if (team != null) {
            textViewTeamName.setText(team.name)
        }
    }
}