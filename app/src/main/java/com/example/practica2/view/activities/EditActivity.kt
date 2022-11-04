package com.example.practica2.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practica2.databinding.ActivityEditBinding
import com.example.practica2.db.DbTeams
import com.example.practica2.model.Team

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding

    private lateinit var dbTeams: DbTeams
    var game: Team? = null
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        if(bundle!=null){
            id = bundle.getInt("ID",0)
        }

        dbTeams = DbTeams(this)

        game = dbTeams.getTeam(id)

        game?.let{ game ->
            with(binding){
                tietTeamName.setText(game.name)
                tietTeamDivision.setText(game.division)
                tietTeamConference.setText(game.conference)
                tietTeamTitles.setText(game.titles.toString())

            }
        }
    }

    fun click(view: View) {
        with(binding){
            when{
                tietTeamName.text.toString().isEmpty() -> {
                    tietTeamName.error = "Can not be empty"
                    Toast.makeText(this@EditActivity, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
                tietTeamDivision.text.toString().isEmpty() -> {
                    tietTeamDivision.error = "Can not be empty"
                    Toast.makeText(this@EditActivity, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
                tietTeamConference.text.toString().isEmpty() -> {
                    tietTeamConference.error = "Can not be empty"
                    Toast.makeText(this@EditActivity, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
                tietTeamTitles.text.toString().isEmpty() -> {
                    tietTeamTitles.error = "Can not be empty"
                    Toast.makeText(this@EditActivity, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    if(dbTeams.updateTeam(id, tietTeamName.text.toString(), tietTeamDivision.text.toString(), tietTeamConference.text.toString(), tietTeamTitles.text.toString().toInt())){
                        Toast.makeText(this@EditActivity, "Registro actualizado exitosamente", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@EditActivity, DetailsActivity::class.java)
                        intent.putExtra("ID", id)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this@EditActivity, "Error al actualizar el registro", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, DetailsActivity::class.java))
    }
}