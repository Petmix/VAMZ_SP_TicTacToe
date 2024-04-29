package com.example.tictactoe

data class TTTState(
    val player1Name: String = "",
    val player1Score: Int = 0,
    val player2Name: String = "",
    val player2Score: Int = 0,
    val numOfGames: Int = 1,
    val numOfGamesPlayed: Int = 0,
    val isGameOver: Boolean = false
)
