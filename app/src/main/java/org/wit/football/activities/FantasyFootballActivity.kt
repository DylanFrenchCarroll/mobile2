package org.wit.football.activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fantasy.*

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.football.R
import org.wit.placemark.app.models.TeamModel

class FantasyFootballActivity : AppCompatActivity(), AnkoLogger {

//  var team  = TeamModel()
//  val teams = ArrayList<TeamModel>();

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fantasy)


    btnListTeamsMenu.setOnClickListener() {
      info("List Button Pressed:")

    }

    btnCreateTeamMenu.setOnClickListener() {
      info("Create Button Pressed:")
      startActivity(Intent(this@FantasyFootballActivity, CreateTeamActivity::class.java))
    }

  }
}