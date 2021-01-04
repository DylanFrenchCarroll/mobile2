package org.wit.placemark.app.models

import org.wit.football.models.PlayerModel
import java.io.Serializable

data class TeamModel(var id: Int, var name: String = "", var players: ArrayList<PlayerModel>) :
    Serializable {

    override fun toString(): String = "### ID: $id  Team: $name --- Players: $players \n"
}