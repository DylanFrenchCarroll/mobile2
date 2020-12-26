package org.wit.football.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.football.R
import org.wit.placemark.app.models.TeamModel
import java.lang.String


interface TeamListener {
    fun onTeamClick(team: TeamModel)
}


class TeamAdapter(private val mCtx: Context, teamList: List<TeamModel>) : RecyclerView.Adapter<TeamAdapter.TeamViewHolder?>(), AnkoLogger {




    private var teamList: List<TeamModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        //inflating and returning our view holder
        val inflater = LayoutInflater.from(mCtx)
        val view: View = inflater.inflate(R.layout.card_team, null)
        return TeamViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {



        val team: TeamModel = teamList[position]
        lateinit var listener: TeamListener
        holder.textViewName.setText(team.name)
        holder.itemView.setOnClickListener {
           info(team)
            //Open new activity to edit team here
        }


    }

    override fun getItemCount(): Int {
        return teamList.size
    }

    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewName: TextView

        init {
            textViewName = itemView.findViewById(R.id.teamName)
        }

        fun bind(team: TeamModel, listener: TeamListener) {
            itemView.setOnClickListener { listener.onTeamClick(team) }
        }
    }







    init {
        this.teamList = teamList
    }


}