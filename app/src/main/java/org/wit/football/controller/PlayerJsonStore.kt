import android.content.Context
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
    val context: Context


    constructor (context: Context) {
        this.context = context
        if (exists(context, PLAYER_JSON_FILE)) {
            playerDeserialize()
        }
    }

    fun playerCreateDB() {
        players.add(PlayerModel("Timo Werner", 25, "Chelsea"))
        players.add(PlayerModel("Bruno Fernandes", 25, "Manchester United"))
        players.add(PlayerModel("Jack Grealish", 22, "Aston Villa"))
        players.add(PlayerModel("David Luiz", 28, "Arsenal"))
        players.add(PlayerModel("Jamie Vardy", 33, "Leicester"))
        players.add(PlayerModel("Kevin De Bruyne", 29, "Manchester City"))
        info("Creating DB")
        playerSerialize()
    }

    fun playerFindAll(): ArrayList<PlayerModel> {
        return players
    }

    fun playerFindOne(name: String): PlayerModel? {

        var foundPlayer = players.find { p -> p.name == name }
        if(foundPlayer != null){
            return foundPlayer
        }else return null


    }


    private fun playerSerialize() {
        val jsonString = playerGsonBuilder.toJson(players, playerListType)
        info("JSONSTRING: " + jsonString)
        info("Inside serialise Statement")
        write(context, PLAYER_JSON_FILE, jsonString)
    }

    private fun playerDeserialize() {
        val jsonString = read(context, PLAYER_JSON_FILE)
        players = Gson().fromJson(jsonString, playerListType)
    }
}