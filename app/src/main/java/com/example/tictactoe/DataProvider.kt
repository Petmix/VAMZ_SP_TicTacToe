package com.example.tictactoe

object DataProvider
{
    val game = TTTState(
        id = 0,
        player1Name = "Leo",
        player1Score = 3,
        player2Name = "Mia",
        player2Score = 2,
        date = "05.05.2024",
        time = "19:27:42"
    )

    val gameList = listOf(
        game,
        TTTState(
            id = 1,
            player1Name = "Leo",
            player1Score = 3,
            player2Name = "Mia",
            player2Score = 5,
            date = "05.05.2024",
            time = "20:00:11"
        ),
        TTTState(
            id = 2,
            player1Name = "Mia",
            player1Score = 1,
            player2Name = "Leo",
            player2Score = 2,
            date = "05.05.2024",
            time = "20:12:01"
        ),
        TTTState(
            id = 3,
            player1Name = "Mia",
            player1Score = 2,
            player2Name = "Leo",
            player2Score = 2,
            date = "05.05.2024",
            time = "20:14:52"
        ),
        TTTState(
            id = 4,
            player1Name = "Leo",
            player1Score = 3,
            player2Name = "Mia",
            player2Score = 5,
            date = "05.05.2024",
            time = "21:17:19"
        ),
        TTTState(
            id = 5,
            player1Name = "Leo",
            player1Score = 1,
            player2Name = "Mia",
            player2Score = 4,
            date = "06.05.2024",
            time = "10:15:23"
        )
    )
}