package org.wit.football.controller



import TeamJsonStore
import org.wit.placemark.app.models.TeamModel



val teams =  TeamJsonStore()


open class TeamsCRUD{
    fun addTeam(team: TeamModel){
        println("You Chose Add Team")
        teams.teamCreate(team)
    }

    fun updateTeams(team: TeamModel, newTeam: TeamModel) {
        println("You Chose Update Team")
        teams.teamUpdate(team, newTeam)
    }
    fun deleteTeams(team: TeamModel) {
        println("You Chose Delete Team")
        teams.teamDelete(team)
    }

    fun getAllTeams() : List<TeamModel> {
        return teams.teamFindAll()
    }
    fun searchTeams(name: String) : List<TeamModel>{
        println("Searched: $name")
        val fullList = teams.teamFindAll()
        val filteredList = fullList.filter { it.name.contains(name) }
        return filteredList
    }





}