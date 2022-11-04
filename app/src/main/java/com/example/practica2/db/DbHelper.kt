package com.example.practica2.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class DbHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "teamsFootball.db"
        private const val TABLE_TEAMS = "teams"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE $TABLE_TEAMS (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "division TEXT NOT NULL, " +
                "conference TEXT NOT NULL, " +
                "titles INTEGER NOT NULL )"
            )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE $TABLE_TEAMS")
        onCreate(db)
    }

}