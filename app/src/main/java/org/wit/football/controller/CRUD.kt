package org.wit.football.controller





import JSONStore
import JsonHelper
import org.wit.football.models.PlayerModel


val jsonHelper = JsonHelper();

val players = JSONStore()


open class Crud{
    fun addPlayer(player: PlayerModel){
        println("You Chose Add Player")
        players.create(player)
    }

    fun updatePlayer(player: PlayerModel, newPlayer: PlayerModel) {
        println("You Chose Update Player")
        players.update(player, newPlayer)
    }
    fun deletePlayer(player: PlayerModel) {
        println("You Chose Delete Player")
        players.delete(player)
    }

    fun getAllPlayers() : List<PlayerModel> {
        return players.findAll()
    }
//    fun searchPlayers(name: String) : List<PlayerModel>{
//        println("Searched: $name")
//        val fullList = getAllPlayers()
//        val filteredList = fullList.filter { it.name.contains(name) }
//        return filteredList
//
//    }





}