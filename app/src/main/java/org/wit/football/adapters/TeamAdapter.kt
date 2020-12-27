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
import org.wit.placemark.app.models.TeamModel
import java.io.Serializable


class TeamAdapter(private val mCtx: Context, teamList: List<TeamModel>) :
    RecyclerView.Adapter<TeamAdapter.TeamViewHolder?>(), AnkoLogger {


    private var teamList: List<TeamModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {

        val inflater = LayoutInflater.from(mCtx)
        val view: View = inflater.inflate(R.layout.card_team, null)
        return TeamViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team: TeamModel = teamList[position]
        holder.textViewName.setText(team.name)
        holder.itemView.setOnClickListener {
            info("HERE" + team)
            var i = Intent(mCtx, EditTeamActivity::class.java)
            i.putExtra("myTeam", team)
            mCtx.startActivity(i)
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
    }


    init {
        this.teamList = teamList
    }


}