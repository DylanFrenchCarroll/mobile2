package org.wit.placemark.app.models

import org.wit.football.models.PlayerModel


data class TeamModel(
        var name: String = "",
        var players: Array<PlayerModel>
){
    override fun toString(): String = "### Team: $name --- Players: $players"
}