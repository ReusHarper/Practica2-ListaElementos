package com.example.practica2.view.activities

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practica2.R
import com.example.practica2.databinding.ActivityMainBinding
import com.example.practica2.db.DbTeams
import com.example.practica2.model.Team
import com.example.practica2.view.adapters.TeamAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //private var teamMutableList: MutableList<Team> = TeamsProvider.teamList.toMutableList()
    private lateinit var listTeams: ArrayList<Team>

    //private lateinit var adapter: TeamAdapter
    private val llmanager = LinearLayoutManager(this)

    lateinit var dbTeams: DbTeams
    var team: Team? = null
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.btnAddTeam.setOnClickListener { createTeam() }
        //initRecyclerView()

        val dbTeams = DbTeams(this)
        listTeams = dbTeams.getTeams()

        val teamsAdapter = TeamAdapter(this, listTeams)
        binding.rvTeams.layoutManager = LinearLayoutManager(this)
        binding.rvTeams.adapter = teamsAdapter

        if(listTeams.size == 0) binding.tvSinRegistros.visibility = View.VISIBLE
        else binding.tvSinRegistros.visibility = View.INVISIBLE
    }

    fun click(view: View) {
        // Obtencion del item seleccionado
        when(view.id){
            R.id.btnAddTeam -> {
                startActivity(Intent(this, AddActivity::class.java))
                finish()
            }
        }
    }

    fun selectedTeam(team: Team){
        //Manejamos el click del elemento en el recycler view
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("ID", team.id)

        startActivity(intent)
        finish()
    }

}