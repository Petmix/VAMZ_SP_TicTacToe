package com.example.tictactoe

import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class GamePlay : ViewModel()
{
    private val _uiState = MutableStateFlow(TTTState())
    private var _playerTurn = true
    private var _moves = mutableListOf(true, false, true, false, true, false, true, false, true)

    init {
        _uiState.value = TTTState()
    }

    fun resetGame()
    {
        _uiState.value = TTTState()
        _moves = mutableListOf(true, false, true, false, true, false, true, false, true)
    }

    fun setWhoGoesFirst(goesFirst: Boolean)
    {
        _playerTurn = goesFirst
        setupMoves(goesFirst)
    }

    private fun setupMoves(first: Boolean)
    {
        if (!first)
        {
            for (i in 0..8)
            {
                if (_moves[i])
                {
                    _moves[i] = false
                }
                else
                {
                    _moves[i] = true
                }
            }
        }
    }

    fun checkButton(num: Int)
    {

    }

    fun getPlayerTurn() : Boolean
    {
        return _playerTurn
    }

    fun getPlayer1Name() : String
    {
        return _uiState.value.player1Name
    }

    fun getPlayer2Name() : String
    {
        return _uiState.value.player2Name
    }

    fun getPlayer1Score() : Int
    {
        return _uiState.value.player1Score
    }

    fun getPlayer2Score() : Int
    {
        return _uiState.value.player2Score
    }

    fun getNumOfGames() : Int
    {
        return _uiState.value.numOfGames
    }

    fun getNumOfGamesPlayed() : Int
    {
        return _uiState.value.numOfGamesPlayed
    }
}