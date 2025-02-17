package org.wit.football.adapters

import PlayerJsonStore
import TeamJsonStore
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.football.R
import org.wit.football.models.PlayerModel
import org.wit.placemark.app.models.TeamModel
import java.io.Serializable


class PlayerAdapter(private val mCtx: Context, playerList: List<PlayerModel>, team: TeamModel) :
    RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder?>(), Serializable, AnkoLogger {

    private var playerList: List<PlayerModel>
    var team = team
    var squadList: ArrayList<PlayerModel> = ArrayList()


    init {
        this.playerList = playerList
        for (player1 in team.players) {
            var name = player1.name as String
            var foundPlayer = playerList.find { p -> p.name == name }
            if (foundPlayer != null) {
                playerList.find { p -> p.name == name }?.isSelected = true
                foundPlayer.isSelected = true
                squadList.add(foundPlayer)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val inflater = LayoutInflater.from(mCtx)
        val view: View = inflater.inflate(R.layout.card_player, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player: PlayerModel = playerList[position]
        holder.itemView.setBackgroundColor(Color.parseColor("#30475e"))
        holder.playerTextViewName.setText(player.name)
        if (player.isSelected == true) {
            holder.itemView.setBackgroundColor(Color.CYAN)
        }


        holder.itemView.setOnClickListener {
            var boolean: Boolean = player.isSelected
            player.setPlayerSelected(!boolean);
            if (player.isPlayerSelected() && squadList.size < 5) {
                holder.itemView.setBackgroundColor(Color.CYAN)
                squadList.add(player)
            } else {
                holder.itemView.setBackgroundColor(Color.parseColor("#30475e"))
                squadList.remove(player)
            }
        }
    }

    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), AnkoLogger {
        var playerTextViewName: TextView
        init {
            playerTextViewName = itemView.findViewById(R.id.playerName)
        }
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    fun getNewSquad(): ArrayList<PlayerModel> {
        return squadList
    }


}