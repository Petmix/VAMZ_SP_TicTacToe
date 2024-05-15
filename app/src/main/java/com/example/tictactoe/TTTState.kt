package com.example.tictactoe

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Dátová trieda, ktorá reprezentuje 1 záznam hry v databáze.
 */
@Entity(tableName = "games")
data class TTTState(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // automaticky generované jedinečné ID
    var player1Name: String = "", // meno hráča 1
    var player1Score: Int = 0, // skóre hráča 1
    var player2Name: String = "", // meno hráča 2
    var player2Score: Int = 0, // skóre hráča 2
    var numberOfGamesPlayed: Int = 0, // počet odohraných hier
    var date: String = "", // dátum odohrania hry
    var time: String = "" // čas odohrania hry
)