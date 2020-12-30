
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.football.models.PlayerModel
import org.wit.placemark.app.models.TeamModel
import java.util.*



var TEAM_JSON_FILE = "teams.json"
var teamGsonBuilder = GsonBuilder().setPrettyPrinting().create()
var teamListType = object : TypeToken<java.util.ArrayList<TeamModel>>() {}.type



class TeamJsonStore : AnkoLogger {

    var teams = mutableListOf<TeamModel>()
    val context: Context


    fun teamGenerateRandomId(): Int {
        return Random().nextInt()
    }


    constructor (context: Context) {
        this.context = context
        if (exists(context, TEAM_JSON_FILE)) {
            teamDeserialize()
        }
    }

    fun teamFindAll(): MutableList<TeamModel> {
        return teams
    }


    fun teamFindOne(id: Int) : TeamModel? {
        var foundTeam = teams.find { p -> p.id == id }
        return foundTeam
    }

    fun teamCreate(team: TeamModel) {
        info("creating team name: " + team.name + " and id: " + team.id)
        teams.add(team)
        info("addedTeam#################")
        teamSerialize()
    }

    fun teamValidateSize(team: TeamModel): Int {
        return teams.size
    }

    fun teamUpdateName(oldTeam: TeamModel, newTeamName: String) {
        var foundTeam = teamFindOne(oldTeam.id)
        if (foundTeam != null) {
            oldTeam.name = newTeamName
        }
        teamSerialize()
    }

    fun teamUpdatePlayers(oldTeam: TeamModel, newTeamPlayers: ArrayList<PlayerModel>) {
        var foundTeam = teamFindOne(oldTeam.id)
        if (foundTeam != null) {
            oldTeam.players = newTeamPlayers
        }
        teamSerialize()
    }


    fun teamDelete(team: TeamModel) {
        teams.remove(team)
        teamSerialize()
    }


    private fun teamSerialize() {
        val jsonString = teamGsonBuilder.toJson(teams, teamListType)
        write(context,TEAM_JSON_FILE, jsonString)
    }



    private fun teamDeserialize() {
        val jsonString = read(context, TEAM_JSON_FILE)
        teams = Gson().fromJson(jsonString, teamListType)

    }
}