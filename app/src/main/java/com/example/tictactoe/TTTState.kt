package com.example.tictactoe

data class TTTState(
    var player1Name: String = "",
    var player1Score: Int = 0,
    var player2Name: String = "",
    var player2Score: Int = 0,
    var numOfGames: Int = 1,
    var numOfGamesPlayed: Int = 0
)
