
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.placemark.app.models.TeamModel
import java.util.*



var TEAM_JSON_FILE = "teams.json"
var teamGsonBuilder = GsonBuilder().setPrettyPrinting().create()
var teamListType = object : TypeToken<java.util.ArrayList<TeamModel>>() {}.type

fun teamGenerateRandomId(): Int {
    return Random().nextInt()
}

class TeamJsonStore : AnkoLogger {

    var teams = mutableListOf<TeamModel>()
    val context: Context



    constructor (context: Context) {
        this.context = context
        if (exists(context, TEAM_JSON_FILE)) {
            teamDeserialize()
        }
    }

    fun teamFindAll(): MutableList<TeamModel> {
        return teams
    }

    fun teamFindOne(name: String) : TeamModel? {
        var foundTeam = teams.find { p -> p.name == name }
        return foundTeam
    }

    fun teamCreate(team: TeamModel) {
        teams.add(team)
        teamSerialize()
    }
    fun teamValidateSize(team: TeamModel): Int {
        return teams.size
    }

    fun teamUpdate(team: TeamModel, newTeam: TeamModel) {
        var foundPlayer = teamFindOne(team.name)
        if (foundPlayer != null) {
            teamDelete(team)
            teamCreate(newTeam)
        }
        teamSerialize()
    }


    fun teamDelete(team: TeamModel) {
        teams.remove(team)
        teamSerialize()
    }


//    internal fun teamLogAll() {
//      teams.forEach{logger.info(it.toString())}
//    }

    private fun teamSerialize() {
        val jsonString = teamGsonBuilder.toJson(teams, teamListType)
        info("JSONSTRING: " + jsonString)
        write(context,TEAM_JSON_FILE, jsonString)
    }

    private fun teamDeserialize() {
        val jsonString = read(context, TEAM_JSON_FILE)
        teams = Gson().fromJson(jsonString, teamListType)

    }
}