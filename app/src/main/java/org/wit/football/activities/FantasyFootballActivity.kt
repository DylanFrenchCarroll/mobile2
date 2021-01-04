package org.wit.football.activities


import PlayerJsonStore
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import exists
import kotlinx.android.synthetic.main.activity_fantasy.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.football.R
import org.wit.placemark.app.models.TeamModel


class FantasyFootballActivity : AppCompatActivity(), AnkoLogger {

    lateinit var team: TeamModel
    var PlayerOperations: PlayerJsonStore = PlayerJsonStore()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fantasy)

        //getting players for good
        PlayerOperations.getPlayers()

        btnListTeamsMenu.setOnClickListener() {
            info("DB PLAYER LISTTTTTTTTTTTTTTTTTTTTTT  HEREa!!!!!!!" +  PlayerOperations.playerFindAll())
            startActivity(Intent(this@FantasyFootballActivity, ListTeamsActivity::class.java))
        }

        btnCreateTeamMenu.setOnClickListener() {
            startActivity(Intent(this@FantasyFootballActivity, CreateTeamActivity::class.java))
        }
    }

}
