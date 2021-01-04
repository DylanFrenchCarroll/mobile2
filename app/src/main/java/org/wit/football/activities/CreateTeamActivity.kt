package org.wit.football.activities

import TeamJsonStore
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_create_team.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.football.R
import org.wit.football.models.PlayerModel
import org.wit.placemark.app.models.TeamModel

class CreateTeamActivity : AppCompatActivity(), AnkoLogger {

    val players = ArrayList<PlayerModel>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_team)
        val context = getApplicationContext();
        val TeamOperations = TeamJsonStore(context)
        var team = TeamModel(TeamOperations.teamGenerateRandomId(),"EmptyName", players)

        btn_createATeam.setOnClickListener() {
            team.name = teamName.text.toString()
            TeamOperations.teamCreate(team)
            var i = Intent(context, EditTeamActivity::class.java)
            i.putExtra("myTeam", team)
            i.putExtra("case", "CreateList")
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i)
        }


        btn_createGoBack.setOnClickListener(){
            var i = Intent(context, FantasyFootballActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i)
        }



    }


}