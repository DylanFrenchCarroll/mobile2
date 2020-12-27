package org.wit.football.activities


import TeamJsonStore
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_create_team.*
import kotlinx.android.synthetic.main.activity_fantasy.*

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.football.R

import org.wit.football.models.PlayerModel
import org.wit.placemark.app.models.TeamModel

class CreateTeamActivity : AppCompatActivity(), AnkoLogger {


    val teams = ArrayList<TeamModel>();
    val players = ArrayList<PlayerModel>();
    var team = TeamModel("EmptyName", players)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_team)
        val context = getApplicationContext();
        val TeamOperations = TeamJsonStore(context)



        btn_createATeam.setOnClickListener() {
            info("Create Button Pressed:")
            team.name = teamName.text.toString()
            info(team.name)
            TeamOperations.teamCreate(team)
            info("Team Added")


            var i = Intent(context, EditTeamActivity::class.java)
            i.putExtra("myTeam", team)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i)

        }
    }
}