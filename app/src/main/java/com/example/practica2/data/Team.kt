package com.example.practica2.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// Creacion de la entidad team
@Entity(tableName = "team_table")
data class Team (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val division: String,
    val conference: String,
    val logo: String,
    val titles: Int,
)