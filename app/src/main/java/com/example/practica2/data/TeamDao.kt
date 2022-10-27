package com.example.practica2.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// Generacion de los metodos que deberan ejecutarse cuando se ejecuten ciertas instrucciones SQL
@Dao
interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTeam(team: Team)

    @Query("SELECT * FROM team_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Team>>

}