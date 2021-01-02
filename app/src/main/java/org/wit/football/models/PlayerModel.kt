package org.wit.football.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class PlayerModel(
    var name: String?,
    var age: Int,
    var team: String?,
    var isSelected: Boolean = false
) : Serializable {


    override fun toString(): String {
        return "Player: \n Name:'$name' \n Age:$age \n Team:'$team"
    }

    fun setPlayerSelected(selected: Boolean) {
        isSelected = selected
    }

    fun isPlayerSelected(): Boolean {
        return isSelected
    }


}

