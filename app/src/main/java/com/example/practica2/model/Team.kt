package com.example.practica2.model

// Creacion de la entidad team
//  @Entity(tableName = "team_table")
data class Team (
    //@PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val division: String,
    val conference: String,
    val titles: Int,
    val logo: String?,
)