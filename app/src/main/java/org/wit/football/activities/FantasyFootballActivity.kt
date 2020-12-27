package org.wit.football.activities


import PlayerJsonStore
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import exists
import kotlinx.android.synthetic.main.activity_fantasy.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.json.JSONException
import org.json.JSONObject
import org.wit.football.R


class FantasyFootballActivity : AppCompatActivity(), AnkoLogger {






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fantasy)
        var context: Context = getApplicationContext();

        if (!exists(context, "players.json")) {
            info("Inside If Statement")
            var PlayerOperations: PlayerJsonStore = PlayerJsonStore(context)
            PlayerOperations.playerCreateDB()

        }

        btnListTeamsMenu.setOnClickListener() {
            info("List Button Pressed:")
            startActivity(Intent(this@FantasyFootballActivity, ListTeamsActivity::class.java))
        }

        btnCreateTeamMenu.setOnClickListener() {
            info("Create Button Pressed:")
            startActivity(Intent(this@FantasyFootballActivity, CreateTeamActivity::class.java))
        }

    }


    fun writeJSON() {
        val `object` = JSONObject()
        try {
            `object`.put("name", "Jack Hack")
            `object`.put("age", 24)
            `object`.put("team", "Arsenal")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        println(`object`)
    }










}
