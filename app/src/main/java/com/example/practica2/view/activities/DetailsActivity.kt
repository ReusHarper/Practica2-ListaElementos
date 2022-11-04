package com.example.practica2.view.activities

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.practica2.R
import com.example.practica2.databinding.ActivityDetailsBinding
import com.example.practica2.db.DbTeams
import com.example.practica2.model.Team

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private lateinit var dbTeams: DbTeams
    var team: Team? = null
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        if(bundle!=null){
            id = bundle.getInt("ID",0)

        }

        dbTeams = DbTeams(this)
        team = dbTeams.getTeam(id)

        team?.let{ team ->
            with(binding){
                tietTeamName.setText(team.name)
                tietTeamDivision.setText(team.division)
                tietTeamConference.setText(team.conference)
                tietTeamTitles.setText(team.titles.toString())

                //para que se desactive el teclado en los edittext
                tietTeamName.inputType = InputType.TYPE_NULL
                tietTeamDivision.inputType = InputType.TYPE_NULL
                tietTeamConference.inputType = InputType.TYPE_NULL
                tietTeamTitles.inputType = InputType.TYPE_NULL
            }
        }

    }

    fun click(view: View) {
        when(view.id){
            R.id.btnTeamEdit -> {
                val intent = Intent(this, EditActivity::class.java)
                intent.putExtra("ID", id)
                startActivity(intent)
                finish()
            }
            R.id.btnTeamDelete -> {
                AlertDialog.Builder(this)
                    .setTitle("Confirm")
                    .setMessage("Do you really want to delete the game ${team?.name}?")
                    .setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, which ->
                        if(dbTeams.deleteTeam(id)){
                            Toast.makeText(this@DetailsActivity, "Registry deleted successfully", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@DetailsActivity, MainActivity::class.java))
                            finish()
                        }else{
                            Toast.makeText(this@DetailsActivity, "Delete failed", Toast.LENGTH_SHORT).show()
                        }
                    })
                    .setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                    })
                    .show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }
}