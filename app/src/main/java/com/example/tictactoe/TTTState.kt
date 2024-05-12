package com.example.tictactoe

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "games")
data class TTTState(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var player1Name: String = "",
    var player1Score: Int = 0,
    var player2Name: String = "",
    var player2Score: Int = 0,
    var numberOfGamesPlayed: Int = 0,
    var date: String = "",
    var time: String = ""
)