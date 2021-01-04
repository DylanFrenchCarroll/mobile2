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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fantasy)
        var context: Context = getApplicationContext();

        if (!exists(context, "players.json")) {
            var PlayerOperations: PlayerJsonStore = PlayerJsonStore(context)
            PlayerOperations.playerCreateDB()
        }



        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        info("DB HERE: #############: " + db)
        db.collection("players").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result!!) {
                    info(document.id + " => " + document.data)
                }
            } else {
                info("Error getting documents.", task.exception)
            }
        }
        info("FUCKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKing work")


        btnListTeamsMenu.setOnClickListener() {
            startActivity(Intent(this@FantasyFootballActivity, ListTeamsActivity::class.java))
        }

        btnCreateTeamMenu.setOnClickListener() {
            startActivity(Intent(this@FantasyFootballActivity, CreateTeamActivity::class.java))
        }

    }

}
