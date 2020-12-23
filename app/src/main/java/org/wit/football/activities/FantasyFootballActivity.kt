package org.wit.football.activities


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

    btnAdd.setOnClickListener() {
      info("Add Button Pressed:")
//      team.name = teamName.text.toString()
//      if (team.name.isNotEmpty()) {
//        teams.add(team)
//        info("Add Button Pressed: $team.name")
//        for (i in teams.indices) {
//          info("Team[$i]:${this.teams[i]}")
//        }
//      }
//      else {
//        toast ("Please Enter a name")
//      }
    }
  }
}