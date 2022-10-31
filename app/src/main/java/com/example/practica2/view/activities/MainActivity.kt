package com.example.practica2.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practica2.databinding.ActivityMainBinding
import com.example.practica2.model.Team
import com.example.practica2.view.adapters.TeamAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(this)

        // El LinearLayoutManager se encarga de mostrar los items de manera en forma de listado unilateral
        binding.rvTeams.layoutManager = manager
        // Pasamos los items creados desde el TeamsProvider al adapter
        binding.rvTeams.adapter =
            TeamAdapter(TeamsProvider.teamList) { team ->
                onItemSelected(team)
            }
    }

    fun onItemSelected(team: Team) {
        Toast.makeText(
            this,
            team.name,
            Toast.LENGTH_SHORT
        ).show()
    }

    fun click(view: View) {
        startActivity(Intent(this, AddActivity::class.java))
        finish()
    }

}