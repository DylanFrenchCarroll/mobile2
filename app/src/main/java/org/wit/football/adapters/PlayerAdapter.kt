package org.wit.football.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.football.R
import org.wit.football.activities.EditTeamActivity
import org.wit.football.models.PlayerModel
import org.wit.placemark.app.models.TeamModel
import java.io.Serializable


class PlayerAdapter(private val mCtx: Context, playerList: List<PlayerModel>) :
    RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder?>(), AnkoLogger {


    private lateinit var playerList: List<PlayerModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {

        val inflater = LayoutInflater.from(mCtx)
        val view: View = inflater.inflate(R.layout.card_player, null)
        return PlayerViewHolder(view)
    }

     override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player: PlayerModel = playerList[position]
        holder.playerTextViewName.setText(player.name)
        holder.itemView.setOnClickListener {
            info("HERE" + player)
//            var i = Intent(mCtx, EditTeamActivity::class.java)
//            i.putExtra("myTeam", team)
//            mCtx.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return playerList.size
    }



    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var playerTextViewName: TextView
        init {
            playerTextViewName = itemView.findViewById(R.id.playerName)
        }
    }


    init {
        this.playerList = playerList
    }


}