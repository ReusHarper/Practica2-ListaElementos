package com.example.practica2.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practica2.databinding.ActivityAddBinding
import com.example.practica2.db.DbTeams

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun clickAdd(view: View) {

        val tx_fillFields = "Please fill all fields"
        val tx_saved = "Team saved successfully"
        val tx_error = "Error saving team"

        val dbGames = DbTeams(this)
            with(binding){
                when{
                    tietTeamName.text.toString().isEmpty() -> { Toast.makeText(this@AddActivity, tx_fillFields, Toast.LENGTH_SHORT).show() }
                    tietTeamDivision.text.toString().isEmpty() -> { Toast.makeText(this@AddActivity, tx_fillFields, Toast.LENGTH_SHORT).show() }
                    tietTeamConference.text.toString().isEmpty() -> { Toast.makeText(this@AddActivity, tx_fillFields, Toast.LENGTH_SHORT).show() }
                    tietTeamTitles.text.toString().isEmpty() -> { Toast.makeText(this@AddActivity, tx_fillFields, Toast.LENGTH_SHORT).show() }
                    else -> {
                        //Realizamos la inserción
                        val id = dbGames.insertTeam(
                            tietTeamName.text.toString(),
                            tietTeamDivision.text.toString(),
                            tietTeamConference.text.toString(),
                            tietTeamTitles.text.toString().toInt()
                        )

                        if(id > 0){
                            Toast.makeText(this@AddActivity, tx_saved, Toast.LENGTH_SHORT).show()
                            tietTeamName.setText("")
                            tietTeamDivision.setText("")
                            tietTeamConference.setText("")
                            tietTeamTitles.setText("")
                        }else{
                            Toast.makeText(this@AddActivity, tx_error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
    }

    fun clickUpdate(view: View) {}

}