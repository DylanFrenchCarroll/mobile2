package org.wit.football.models

data class PlayerModel(var name: String , var age: Int, var team: String){

    override fun toString(): String {
        return "Player: \n Name:'$name' \n Age:$age \n Team:'$team"
    }

}