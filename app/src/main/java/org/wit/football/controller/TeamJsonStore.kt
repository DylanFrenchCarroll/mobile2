
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.wit.placemark.app.models.TeamModel
import java.util.*


val teamJsonHelper = JsonHelper();
var TEAM_JSON_FILE = "teams.json"
var teamGsonBuilder = GsonBuilder().setPrettyPrinting().create()
var teamListType = object : TypeToken<java.util.ArrayList<TeamModel>>() {}.type

fun teamGenerateRandomId(): Int {
    return Random().nextInt()
}

class TeamJsonStore  {

    var teams = mutableListOf<TeamModel>()

    init {
        if (teamJsonHelper.exists(TEAM_JSON_FILE)) {
            teamDeserialize()
        }
        else{
            println("no reading")
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
        teamJsonHelper.write(JSON_FILE, jsonString)
    }

    private fun teamDeserialize() {
        val jsonString = teamJsonHelper.read(JSON_FILE)
        teams = Gson().fromJson(jsonString, teamListType)

    }
}