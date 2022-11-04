package com.example.practica2.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.widget.Toast
import com.example.practica2.model.Team

class DbTeams(private val context: Context): DbHelper(context) {

    //Aquí se van a implementar las operaciones CRUD (Create, Read, Update and Delete)

    fun insertTeam(name: String, division:String, conference: String, titles: Int): Long{
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        var id: Long = 0

        try{
            val values = ContentValues()

            values.put("name", name)
            values.put("division", division)
            values.put("conference", conference)
            values.put("titles", titles)

            id = db.insert("teams", null, values)

        }catch(e: Exception){
            //Manejo de excepción
        }finally {
            db.close()
        }

        return id
    }

    fun getTeams(): ArrayList<Team>{
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        var listTeams = ArrayList<Team>()
        var teamTmp: Team? = null
        var cursorTeams: Cursor? = null

        cursorTeams = db.rawQuery("SELECT * FROM TEAMS", null)

        if(cursorTeams.moveToFirst()){
            do{
                teamTmp = Team(
                    cursorTeams.getInt(0),
                    cursorTeams.getString(1),
                    cursorTeams.getString(2),
                    cursorTeams.getString(3),
                    cursorTeams.getInt(4)
                    //cursorTeams.getString(5),
                )
                listTeams.add(teamTmp)
            }while(cursorTeams.moveToNext())
        }

        cursorTeams.close()

        return listTeams
    }

    fun getTeam(id: Int): Team?{
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        var team: Team? = null
        var cursorTeams: Cursor? = null

        cursorTeams = db.rawQuery("SELECT * FROM TEAMS WHERE id = $id LIMIT 1", null)

        //println("cursorTeams[0] = ${cursorTeams.getInt(0)}, cursorTeams[1] = ${cursorTeams.getString(1)}")

        if(cursorTeams.moveToFirst()){
            team = Team(
                cursorTeams.getInt(0),
                cursorTeams.getString(1),
                cursorTeams.getString(2),
                cursorTeams.getString(3),
                cursorTeams.getInt(4)
            )
        }

        cursorTeams.close()

        println("team.name= ${team!!.name}")

        return team
    }

    fun updateTeam(id: Int, name: String, division: String, conference: String, titles: Int): Boolean{
        var banderaCorrecto = false

        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        println("team xd: ${id}, team name: ${name}")
        println("bandera: $banderaCorrecto")

        try{
            db.execSQL(
                "UPDATE TEAMS SET " +
                        "name = '$name', " +
                        "division = '$division', " +
                        "conference = '$conference', " +
                        "titles = '$titles' " +
                        "WHERE id = $id")

            banderaCorrecto = true
            println("bandera 2: $banderaCorrecto")
        }catch(e: Exception){
            //Manejo de la excepción
        }finally {
            db.close()
        }

        return banderaCorrecto
    }

    fun deleteTeam(id: Int): Boolean{
        var banderaCorrecto = false

        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        try{
            db.execSQL("DELETE FROM TEAMS WHERE id = $id")
            banderaCorrecto = true
        }catch(e: Exception){

        }finally {
            db.close()
        }

        return banderaCorrecto
    }

}