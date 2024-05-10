package com.example.tictactoe

import android.app.Activity
import android.content.Intent.getIntent
import android.content.Intent.getIntentOld
import android.util.Printer
import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.app.ActivityCompat.recreate
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.PrintStream
import java.io.PrintWriter
import kotlin.random.Random

class GamePlay : ViewModel()
{
    private val _uiState = MutableStateFlow(TTTState())
    public val playerTurn = mutableStateOf(true)
    private var _moves = mutableListOf<Boolean?>(null, null, null, null, null, null, null, null, null)
    private var _multiPlayerMode = true

    init {
        _uiState.value = TTTState()
    }

    fun getMyPosition(pos: Int) : Int
    {
        if (_moves[pos] == true) return 1
        if (_moves[pos] == false) return 2
        return 0
    }

    fun resetGame()
    {
        _uiState.value = TTTState()
        for (i in 0..< _moves.size)
        {
            _moves[i] = null
        }
    }

    fun playAgain()
    {
        for (i in 0..< _moves.size)
        {
            _moves[i] = null
        }
    }

    fun setPlayerTurn(turn: Int)
    {
        if (turn == 0) playerTurn.value = chooseRandomTurn()
        else if (turn == 1) playerTurn.value = true
        else playerTurn.value = false
    }

    private fun chooseRandomTurn() : Boolean
    {
        return Random.nextBoolean()
    }

    fun setMove(move: Int)
    {
        _moves[move] = playerTurn.value
    }

    fun goNext()
    {
        playerTurn.value = !playerTurn.value
    }

    fun setMode(multiPlayer: Boolean)
    {
        _multiPlayerMode = multiPlayer
    }

    fun getPlayer1Name() : String
    {
        return _uiState.value.player1Name
    }

    fun setPlayer1Name(name: String)
    {
        _uiState.value.player1Name = name
    }

    fun getPlayer2Name() : String
    {
        return _uiState.value.player2Name
    }

    fun setPlayer2Name(name: String)
    {
        _uiState.value.player2Name = name
    }

    fun getPlayer1Score() : Int
    {
        return _uiState.value.player1Score
    }

    fun addScoreToP1()
    {
        _uiState.value.player1Score += 1
    }

    fun getPlayer2Score() : Int
    {
        return _uiState.value.player2Score
    }

    fun addScoreToP2()
    {
        _uiState.value.player2Score += 1
    }

    fun getNumOfGames() : Int
    {
        return _uiState.value.numOfGames
    }

    fun setNumOfGames(num: Int)
    {
        _uiState.value.numOfGames = num
    }

    fun getNumOfGamesPlayed() : Int
    {
        return _uiState.value.numOfGamesPlayed
    }

    private fun addGamePlayed()
    {
        _uiState.value.numOfGamesPlayed += 1
    }

    /*fun checkEnd()
    {
        when(whoWon())
        {
            0 ->
        }
    }*/

    private fun whoWon() : Int
    {
        when(checkGameOver())
        {
            1 -> if (_moves[0] == true) return 1 else return 2
            2 -> if (_moves[3] == true) return 1 else return 2
            3 -> if (_moves[6] == true) return 1 else return 2
            4 -> if (_moves[0] == true) return 1 else return 2
            5 -> if (_moves[1] == true) return 1 else return 2
            6 -> if (_moves[2] == true) return 1 else return 2
            7 -> if (_moves[0] == true) return 1 else return 2
            8 -> if (_moves[2] == true) return 1 else return 2
            9 -> return 3
        }

        return 0
    }

    private fun checkGameOver() : Int
    {
        if (_moves[0] == _moves[1] == _moves[2]) return 1
        if (_moves[3] == _moves[4] == _moves[5]) return 2
        if (_moves[6] == _moves[7] == _moves[8]) return 3

        if (_moves[0] == _moves[3] == _moves[6]) return 4
        if (_moves[1] == _moves[4] == _moves[7]) return 5
        if (_moves[2] == _moves[5] == _moves[8]) return 6

        if (_moves[0] == _moves[4] == _moves[8]) return 7
        if (_moves[2] == _moves[4] == _moves[6]) return 8

        for (i in 0..< _moves.size)
        {
            if (_moves[i] == null) return 0
            else if (_moves[i] != null && i == _moves.size - 1) return 9
        }

        return 0
    }
}