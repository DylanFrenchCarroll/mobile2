package org.wit.football.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.wit.football.R
import org.wit.placemark.app.models.TeamModel
import java.lang.String


interface TeamListener {
    fun onTeamClick(team: TeamModel)
}


class TeamAdapter(private val mCtx: Context, teamList: List<TeamModel>) :
    RecyclerView.Adapter<TeamAdapter.TeamViewHolder?>() {

    //we are storing all the products in a list
    private lateinit var teamList: List<TeamModel>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        //inflating and returning our view holder
        val inflater = LayoutInflater.from(mCtx)
        val view: View = inflater.inflate(R.layout.card_team, null)
        return TeamViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        //getting the product of the specified position
        val team: TeamModel = teamList[position]

        //binding the data with the viewholder views
        holder.textViewName.setText(team.name)

    }

    override fun getItemCount(): Int {
        return teamList.size
    }

    inner class TeamViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        lateinit var textViewName: TextView


        init {
            textViewName = itemView.findViewById(R.id.teamName)

        }
    }

    //getting the context and product list with constructor
    init {
        this.teamList = teamList
    }


}