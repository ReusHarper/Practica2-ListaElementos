package com.example.practica2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Creacion de la Base de Datos
@Database(entities = [Team::class], version = 1, exportSchema = false)
abstract class TeamDatabase: RoomDatabase()  {

    abstract fun teamDao(): TeamDao

    // Se verifica si hay una instancia de la BD y en caso afirmativo se manda dicha instancia,
    // de lo contrario se crea una.
    companion object {
        @Volatile
        private var INSTANCE: TeamDatabase? = null

        fun getDatabase(context: Context): TeamDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TeamDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}