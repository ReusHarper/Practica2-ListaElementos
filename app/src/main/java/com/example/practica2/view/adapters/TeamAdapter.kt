package com.example.practica2.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practica2.R
import com.example.practica2.databinding.ItemTeamBinding
import com.example.practica2.model.Team
import com.example.practica2.view.activities.MainActivity

class TeamAdapter(private val context: Context, val teams: ArrayList<Team>): RecyclerView.Adapter<TeamAdapter.ViewHolder>(){

    private val layoutInflater = LayoutInflater.from(context)

    class ViewHolder(view: ItemTeamBinding): RecyclerView.ViewHolder(view.root){
        val tvName = view.tvTeamName
        val tvDivision = view.tvTeamDivision
        val tvConference = view.tvTeamConference
        val tvTitle = view.tvTeamTitles
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTeamBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvName.text = teams[position].name
        holder.tvDivision.text = teams[position].division
        holder.tvConference.text = teams[position].conference
        holder.tvTitle.text = teams[position].titles.toString()

        //Para los clicks de cada elemento viewholder

        holder.itemView.setOnClickListener {
            //Manejar el click
            if(context is MainActivity) context.selectedTeam(teams[position])
        }

    }

    override fun getItemCount(): Int {
        return teams.size
    }

}





























/*
class TeamAdapter(
    private val teamList : List<Team>,
    private val onClickListener: (Team) -> Unit,
    private val onClickDelete: (Int) -> Unit
) : RecyclerView.Adapter<TeamViewHolder>() {

    // Creacion de la vista de item_team
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TeamViewHolder(layoutInflater.inflate(R.layout.item_team, parent, false))
    }

    // Pasa por cada uno de los items y llama al metodo render pasandole ese item
    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val item = teamList[position]
        holder.render(item, onClickListener, onClickDelete)
    }

    // Obtenemos el tamano del listado de equipos
    override fun getItemCount(): Int = teamList.size

}
*/
