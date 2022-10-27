package com.example.practica2.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeamViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Team>>
    private val repository: TeamRepository

    init {
        val teamDao = TeamDatabase.getDatabase(application).teamDao()
        repository = TeamRepository(teamDao)
        readAllData = repository.readAllData
    }

    fun addTeam(team: Team) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(team)
        }
    }

}