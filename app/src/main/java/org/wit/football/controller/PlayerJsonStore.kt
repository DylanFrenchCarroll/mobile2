import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.football.models.PlayerModel
import java.util.*
import kotlin.collections.ArrayList


var PLAYER_JSON_FILE = "players.json"
var playerGsonBuilder = GsonBuilder().setPrettyPrinting().create()
var playerListType = object : TypeToken<java.util.ArrayList<PlayerModel>>() {}.type

fun playerGenerateRandomId(): Int {
    return Random().nextInt()
}

class PlayerJsonStore : AnkoLogger {

    var players = ArrayList<PlayerModel>()


    fun getPlayers() {
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        db.collection("players").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result!!) {
                    var dbPlayer = PlayerModel(
                        document.data["name"].toString(),
                        document.data["age"].toString().toInt(),
                        document.data["team"].toString()
                    )
                    players.add(dbPlayer)
                }
            } else {
                info("Error getting documents.", task.exception)
            }
        }
    }


    fun playerFindAll(): ArrayList<PlayerModel> {
        getPlayers()
        return players
    }

    fun playerFindOne(name: String): PlayerModel? {
        var foundPlayer = players.find { p -> p.name == name }
        if (foundPlayer != null) {
            return foundPlayer
        } else return null
    }

}