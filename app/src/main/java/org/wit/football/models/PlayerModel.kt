package org.wit.football.models

import android.os.Parcel
import android.os.Parcelable

data class PlayerModel(var name: String?, var age: Int, var team: String?, var isSelected: Boolean = false) : Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun toString(): String {
        return "Player: \n Name:'$name' \n Age:$age \n Team:'$team"
    }

    fun setPlayerSelected(selected: Boolean){
        isSelected = selected
    }

    fun isPlayerSelected(): Boolean {
        return isSelected
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
        parcel.writeString(team)
        parcel.writeByte(if (isSelected) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlayerModel> {
        override fun createFromParcel(parcel: Parcel): PlayerModel {
            return PlayerModel(parcel)
        }

        override fun newArray(size: Int): Array<PlayerModel?> {
            return arrayOfNulls(size)
        }
    }

}