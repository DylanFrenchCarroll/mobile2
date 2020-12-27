package org.wit.placemark.app.models

import org.wit.football.models.PlayerModel
import java.io.Serializable

data class TeamModel(var name: String = "", var players: ArrayList<PlayerModel>) : Serializable {

    override fun toString(): String = "### Team: $name --- Players: $players"

     fun getTeamName() : String {
        return name
    }

    fun getTeamSquadList() : ArrayList<PlayerModel> {
        return players
    }
}