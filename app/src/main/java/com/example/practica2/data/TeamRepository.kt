package com.example.practica2.data

import androidx.lifecycle.LiveData

class TeamRepository(private val teamDao: TeamDao) {

    val readAllData: LiveData<List<Team>> = teamDao.readAllData()

    suspend fun addUser(team: Team) {
        teamDao.addTeam(team)
    }

}