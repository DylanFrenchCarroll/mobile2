
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken



import org.wit.football.models.PlayerModel
import java.util.*




val PlayerJsonHelper = JsonHelper();
var JSON_FILE = "teams.json"
var gsonBuilder = GsonBuilder().setPrettyPrinting().create()
var listType = object : TypeToken<java.util.ArrayList<PlayerModel>>() {}.type

fun generateRandomId(): Int {
    return Random().nextInt()
}

class JSONStore  {

    var players = mutableListOf<PlayerModel>()

    init {
        if (PlayerJsonHelper.exists(JSON_FILE)) {
            deserialize()
        }
        else{
            println("no reading")
        }

    }

    fun findAll(): MutableList<PlayerModel> {
        return players
    }

    fun findOne(name: String) : PlayerModel? {
        var foundPlayer = players.find { p -> p.name == name }
        return foundPlayer
    }

    fun create(player: PlayerModel) {
        players.add(player)
        serialize()
    }

    fun update(player: PlayerModel, newPlayer: PlayerModel) {
        var foundPlayer = findOne(player.name)
        if (foundPlayer != null) {
            delete(player)
            create(newPlayer)
        }
        serialize()
    }


    fun delete(player: PlayerModel) {
        players.remove(player)
        serialize()
    }


    internal fun logAll() {
//        players.forEach{logger.info(it.toString())}
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(players, listType)
        PlayerJsonHelper.write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = PlayerJsonHelper.read(JSON_FILE)
        players = Gson().fromJson(jsonString, listType)

    }
}