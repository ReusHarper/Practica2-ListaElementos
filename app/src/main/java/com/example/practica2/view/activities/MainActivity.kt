package com.example.practica2.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
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
        startActivity(Intent(this, AddActivity::class.java))
        finish()
    }

    fun selectedTeam(team: Team){
        //Manejamos el click del elemento en el recycler view
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("ID", team.id)
        startActivity(intent)
        finish()
    }















































    /*
    private fun initRecyclerView() {
        // Se asigna la lista mutable al adapter para que modifique el contenido del recycler view
        adapter = TeamAdapter(this, listTeams)
        val manager = LinearLayoutManager(this)

        // El LinearLayoutManager se encarga de mostrar los items de manera en forma de listado unilateral
        binding.rvTeams.layoutManager = manager
        // Pasamos los items creados desde el TeamsProvider al adapter
        binding.rvTeams.adapter = adapter
    }

    private fun onItemSelected(team: Team) {
        Toast.makeText(
            this,
            team.name,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun onItemDeleted(position: Int) {
        // Borrado del item en la lista
        teamMutableList.removeAt(position)

        // Notificamos al recyclerview el item eliminado del listado
        adapter.notifyItemRemoved(position)
    }

    // Se manda a llamar a la activity Add cuando se oprima el boton de agregar
    fun onItemAdd(view: View) {
        startActivity(Intent(this, AddActivity::class.java))
        finish()
    }

     */

}