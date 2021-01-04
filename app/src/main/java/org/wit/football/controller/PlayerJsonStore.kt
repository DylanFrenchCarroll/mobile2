import com.google.firebase.firestore.FirebaseFirestore
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.football.models.PlayerModel
import kotlin.collections.ArrayList

class PlayerJsonStore : AnkoLogger {

    var players = ArrayList<PlayerModel>()

    //Gets a list of players from Firestore
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


    //Returns the player List
    fun playerFindAll(): ArrayList<PlayerModel> {
        getPlayers()
        return players
    }
}