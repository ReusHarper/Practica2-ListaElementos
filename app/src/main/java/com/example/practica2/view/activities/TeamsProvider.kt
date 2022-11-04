package com.example.practica2.view.activities

import com.example.practica2.model.Team

class TeamsProvider {
    companion object {
        val teamList = listOf<Team>(
            Team(
                1,
                "49ers",
                "West",
                "NFC",
                5,
                //"https://i.pinimg.com/originals/1c/3e/f8/1c3ef85d33176e88349a0d9db5d9901c.png"
            ),
            Team(
                2,
                "Broncos",
                "West",
                "AFC",
                3,
                //"https://cdn.wallpapersafari.com/85/32/pQ1djF.jpg"
            ),
            Team(
                3,
                "Cowboys",
                "East",
                "NFC",
                5,
                //"https://loodibee.com/wp-content/uploads/nfl-dallas-cowboys-team-logo.png"
            ),
            Team(
                4,
                "Ravens",
                "North",
                "AFC",
                2,
                //.."https://i.pinimg.com/originals/cd/1b/38/cd1b388c2759e8cd33e7242b36ddfae8.jpg"
            )
        )
    }
}