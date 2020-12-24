package org.wit.placemark.app.models

import org.wit.football.models.PlayerModel


data class TeamModel(
        var name: String = "",
        var players: ArrayList<PlayerModel>
){
    override fun toString(): String = "### Team: $name --- Players: $players"
}