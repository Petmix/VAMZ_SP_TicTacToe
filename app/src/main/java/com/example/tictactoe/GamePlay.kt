package com.example.tictactoe

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.random.Random

class GamePlay : ViewModel()
{
    private val _uiState = MutableStateFlow(TTTState())
    val moves = mutableListOf<Boolean?>(null, null, null, null, null, null, null, null, null)
    val playerTurn = mutableStateOf(true)
    var multiPlayerMode = mutableStateOf(true)
    val gameOver = mutableStateOf(false)
    var difficulty = mutableIntStateOf(2)

    init {
        _uiState.value = TTTState()
    }

    fun resetGame()
    {
        _uiState.value = TTTState()
        for (i in 0..< moves.size)
        {
            if (moves[i] != null)
            {
                moves[i] = null
            }
        }
        gameOver.value = false
    }

    fun playAgain()
    {
        for (i in 0..< moves.size)
        {
            if (moves[i] != null)
            {
                moves[i] = null
            }
        }
        addNumOfGamesPlayed()
        gameOver.value = false

        if ((_uiState.value.player1Name == "" && playerTurn.value) ||
            (_uiState.value.player2Name == "" && !playerTurn.value))
        {
            moveAI()
        }
    }

    fun addNumOfGamesPlayed()
    {
        _uiState.value.numberOfGamesPlayed += 1
    }

    fun getNumOfGamesPlayed() : Int
    {
        return _uiState.value.numberOfGamesPlayed
    }

    fun moveAI() : Int
    {
        when (difficulty.intValue)
        {
            1 -> return moveAIEasy()
            2 -> return moveAIMedium()
            3 -> return moveAIHard()
        }
        return 9
    }

    private fun moveAIEasy() : Int
    {
        val turn = !playerTurn.value
        val aiTurn = playerTurn.value
        var result = 9

        if (moves[0] == turn && moves[1] == turn) result = 2
        else if (moves[1] == turn && moves[2] == turn && moves[0] == null) result = 0
        else if (moves[0] == turn && moves[2] == turn && moves[1] == null) result = 1
        else if (moves[3] == turn && moves[4] == turn && moves[5] == null) result = 5
        else if (moves[4] == turn && moves[5] == turn && moves[3] == null) result = 3
        else if (moves[3] == turn && moves[5] == turn && moves[4] == null) result = 4
        else if (moves[6] == turn && moves[7] == turn && moves[8] == null) result = 8
        else if (moves[7] == turn && moves[8] == turn && moves[6] == null) result = 6
        else if (moves[6] == turn && moves[8] == turn && moves[7] == null) result = 7
        else if (moves[0] == turn && moves[4] == turn && moves[8] == null) result = 8
        else if (moves[4] == turn && moves[8] == turn && moves[0] == null) result = 0
        else if (moves[0] == turn && moves[8] == turn && moves[4] == null) result = 4
        else if (moves[2] == turn && moves[4] == turn && moves[6] == null) result = 6
        else if (moves[4] == turn && moves[6] == turn && moves[2] == null) result = 2
        else if (moves[2] == turn && moves[6] == turn && moves[4] == null) result = 4
        else if (moves[0] == turn && moves[3] == turn && moves[6] == null) result = 6
        else if (moves[3] == turn && moves[6] == turn && moves[0] == null) result = 0
        else if (moves[0] == turn && moves[6] == turn && moves[3] == null) result = 3
        else if (moves[1] == turn && moves[4] == turn && moves[7] == null) result = 7
        else if (moves[4] == turn && moves[7] == turn && moves[1] == null) result = 1
        else if (moves[1] == turn && moves[7] == turn && moves[4] == null) result = 4
        else if (moves[2] == turn && moves[5] == turn && moves[8] == null) result = 8
        else if (moves[5] == turn && moves[8] == turn && moves[2] == null) result = 2
        else if (moves[2] == turn && moves[8] == turn && moves[5] == null) result = 5
        else
        {
            for (i in 0..moves.size)
            {
                if (moves[i] == null)
                {
                    result = i
                    break
                }
            }
        }

        moves[result] = aiTurn
        return result
    }

    private fun moveAIMedium() : Int
    {
        val turn = !playerTurn.value
        val aiTurn = playerTurn.value
        var result = 9

        if (moves[0] == turn && moves[1] == turn) result = 2
        else if (moves[1] == turn && moves[2] == turn && moves[0] == null) result = 0
        else if (moves[0] == turn && moves[2] == turn && moves[1] == null) result = 1
        else if (moves[3] == turn && moves[4] == turn && moves[5] == null) result = 5
        else if (moves[4] == turn && moves[5] == turn && moves[3] == null) result = 3
        else if (moves[3] == turn && moves[5] == turn && moves[4] == null) result = 4
        else if (moves[6] == turn && moves[7] == turn && moves[8] == null) result = 8
        else if (moves[7] == turn && moves[8] == turn && moves[6] == null) result = 6
        else if (moves[6] == turn && moves[8] == turn && moves[7] == null) result = 7
        else if (moves[0] == turn && moves[4] == turn && moves[8] == null) result = 8
        else if (moves[4] == turn && moves[8] == turn && moves[0] == null) result = 0
        else if (moves[0] == turn && moves[8] == turn && moves[4] == null) result = 4
        else if (moves[2] == turn && moves[4] == turn && moves[6] == null) result = 6
        else if (moves[4] == turn && moves[6] == turn && moves[2] == null) result = 2
        else if (moves[2] == turn && moves[6] == turn && moves[4] == null) result = 4
        else if (moves[0] == turn && moves[3] == turn && moves[6] == null) result = 6
        else if (moves[3] == turn && moves[6] == turn && moves[0] == null) result = 0
        else if (moves[0] == turn && moves[6] == turn && moves[3] == null) result = 3
        else if (moves[1] == turn && moves[4] == turn && moves[7] == null) result = 7
        else if (moves[4] == turn && moves[7] == turn && moves[1] == null) result = 1
        else if (moves[1] == turn && moves[7] == turn && moves[4] == null) result = 4
        else if (moves[2] == turn && moves[5] == turn && moves[8] == null) result = 8
        else if (moves[5] == turn && moves[8] == turn && moves[2] == null) result = 2
        else if (moves[2] == turn && moves[8] == turn && moves[5] == null) result = 5
        else
        {
            val nulls = mutableListOf<Int>()
            var index = 0
            for (i in 0..8)
            {
                if (moves[i] == null)
                {
                    nulls.add(index, i)
                    index++
                }
            }

            if (nulls.size > 0)
            {
                result = nulls[Random.nextInt(nulls.size)]
            }
        }

        moves[result] = aiTurn
        return 9
    }

    private fun moveAIHard() : Int
    {
        val turn = !playerTurn.value
        val aiTurn = playerTurn.value
        var result = 9

        if (moves[0] == turn && moves[1] == turn) result = 2
        else if (moves[1] == turn && moves[2] == turn && moves[0] == null) result = 0
        else if (moves[0] == turn && moves[2] == turn && moves[1] == null) result = 1
        else if (moves[3] == turn && moves[4] == turn && moves[5] == null) result = 5
        else if (moves[4] == turn && moves[5] == turn && moves[3] == null) result = 3
        else if (moves[3] == turn && moves[5] == turn && moves[4] == null) result = 4
        else if (moves[6] == turn && moves[7] == turn && moves[8] == null) result = 8
        else if (moves[7] == turn && moves[8] == turn && moves[6] == null) result = 6
        else if (moves[6] == turn && moves[8] == turn && moves[7] == null) result = 7
        else if (moves[0] == turn && moves[4] == turn && moves[8] == null) result = 8
        else if (moves[4] == turn && moves[8] == turn && moves[0] == null) result = 0
        else if (moves[0] == turn && moves[8] == turn && moves[4] == null) result = 4
        else if (moves[2] == turn && moves[4] == turn && moves[6] == null) result = 6
        else if (moves[4] == turn && moves[6] == turn && moves[2] == null) result = 2
        else if (moves[2] == turn && moves[6] == turn && moves[4] == null) result = 4
        else if (moves[0] == turn && moves[3] == turn && moves[6] == null) result = 6
        else if (moves[3] == turn && moves[6] == turn && moves[0] == null) result = 0
        else if (moves[0] == turn && moves[6] == turn && moves[3] == null) result = 3
        else if (moves[1] == turn && moves[4] == turn && moves[7] == null) result = 7
        else if (moves[4] == turn && moves[7] == turn && moves[1] == null) result = 1
        else if (moves[1] == turn && moves[7] == turn && moves[4] == null) result = 4
        else if (moves[2] == turn && moves[5] == turn && moves[8] == null) result = 8
        else if (moves[5] == turn && moves[8] == turn && moves[2] == null) result = 2
        else if (moves[2] == turn && moves[8] == turn && moves[5] == null) result = 5
        else
        {
            if (moves[0] == aiTurn && moves[1] == aiTurn) result = 2
            else if (moves[1] == aiTurn && moves[2] == aiTurn && moves[0] == null) result = 0
            else if (moves[3] == aiTurn && moves[4] == aiTurn && moves[5] == null) result = 5
            else if (moves[4] == aiTurn && moves[5] == aiTurn && moves[8] == null) result = 8
            else if (moves[7] == aiTurn && moves[8] == aiTurn && moves[6] == null) result = 6
            else if (moves[0] == aiTurn && moves[4] == aiTurn && moves[8] == null) result = 8
            else if (moves[4] == aiTurn && moves[8] == aiTurn && moves[0] == null) result = 0
            else if (moves[2] == aiTurn && moves[4] == aiTurn && moves[6] == null) result = 6
            else if (moves[4] == aiTurn && moves[6] == aiTurn && moves[2] == null) result = 2
            else
            {
                val corners = mutableListOf<Int>()
                var index = 5
                corners.add(0, 0)
                corners.add(1, 2)
                corners.add(2, 6)
                corners.add(3, 8)
                for (i in 0..3)
                {
                    if (moves[corners[i]] == aiTurn)
                    {
                        index = i
                        break
                    }
                }

                if (index != 5)
                {
                    val nulls = mutableListOf<Int>()
                    var index2 = 0
                    for (i in 0..3)
                    {
                        if (i != index)
                        {
                            if (moves[corners[i]] == null)
                            {
                                nulls.add(index2, corners[i])
                                index2++
                            }
                        }
                    }

                    if (nulls.size > 0)
                    {
                        result = nulls[Random.nextInt(nulls.size)]
                    }
                }
                else
                {
                    val sides = mutableListOf<Int>()
                    var index3 = 5
                    sides.add(0, 0)
                    sides.add(1, 2)
                    sides.add(2, 6)
                    sides.add(3, 8)
                    for (i in 0..3)
                    {
                        if (moves[sides[i]] == aiTurn)
                        {
                            index3 = i
                            break
                        }
                    }

                    if (index3 != 5) {
                        val nulls2 = mutableListOf<Int>()
                        var index4 = 0
                        for (i in 0..3) {
                            if (i != index3) {
                                if (moves[sides[i]] == null) {
                                    nulls2.add(index4, sides[i])
                                    index4++
                                }
                            }
                        }

                        if (nulls2.size > 0) {
                            result = nulls2[Random.nextInt(nulls2.size)]
                        }
                    }
                    else
                    {
                        val nulls3 = mutableListOf<Int>()
                        var index5 = 0
                        for (i in 0..8)
                        {
                            if (moves[i] == null)
                            {
                                nulls3.add(index5, i)
                                index5++
                            }
                        }
                        result = nulls3[Random.nextInt(nulls3.size)]
                    }
                }
            }
        }
        moves[result] = aiTurn
        return result
    }

    fun setPlayerTurn(turn: Int)
    {
        when (turn)
        {
            0 -> playerTurn.value = chooseRandomTurn()
            1 -> playerTurn.value = true
            2 -> playerTurn.value = false
        }
    }

    private fun chooseRandomTurn() : Boolean
    {
        return Random.nextBoolean()
    }

    fun setMove(move: Int)
    {
        moves[move] = playerTurn.value
    }

    fun goNext()
    {
        playerTurn.value = !playerTurn.value
    }

    fun getPlayer1Name() : String
    {
        return _uiState.value.player1Name
    }

    fun setPlayer1Name(name: String)
    {
        if (multiPlayerMode.value)
        {
            if (name == "") _uiState.value.player1Name = "AI"
        }
        else
        {
            _uiState.value.player1Name = name
        }
    }

    fun getPlayer2Name() : String
    {
        return _uiState.value.player2Name
    }

    fun setPlayer2Name(name: String)
    {
        if (multiPlayerMode.value)
        {
            if (name == "") _uiState.value.player2Name = "AI"
        }
        else
        {
            _uiState.value.player2Name = name
        }
    }

    fun getPlayer1Score() : Int
    {
        return _uiState.value.player1Score
    }

    private fun addScoreToP1()
    {
        _uiState.value.player1Score += 1
    }

    fun getPlayer2Score() : Int
    {
        return _uiState.value.player2Score
    }

    private fun addScoreToP2()
    {
        _uiState.value.player2Score += 1
    }


    fun checkEnd()
    {
        val won = whoWon()
        if (won == 1) addScoreToP1() else if (won == 2) addScoreToP2()
        if (won > 0)
        {
            gameOver.value = true
        }
    }

    private fun whoWon() : Int
    {
        when(checkGameOver())
        {
            1 -> if (moves[0] == true) return 1 else if (moves[0] == false) return 2
            2 -> if (moves[4] == true) return 1 else if (moves[4] == false) return 2
            3 -> if (moves[8] == true) return 1 else if (moves[8] == false) return 2
            4 -> return 3
        }

        return 0
    }

    private fun checkGameOver() : Int
    {
        if (moves[0] == moves[1] && moves[1] == moves[2] && moves[0] != null) return 1
        if (moves[3] == moves[4] && moves[4] == moves[5] && moves[3] != null) return 2
        if (moves[6] == moves[7] && moves[7] == moves[8] && moves[6] != null) return 3

        if (moves[0] == moves[3] && moves[3] == moves[6] && moves[0] != null) return 1
        if (moves[1] == moves[4] && moves[4] == moves[7] && moves[1] != null) return 2
        if (moves[2] == moves[5] && moves[5] == moves[8] && moves[2] != null) return 3

        if (moves[0] == moves[4] && moves[4] == moves[8] && moves[0] != null) return 1
        if (moves[2] == moves[4] && moves[4] == moves[6] && moves[2] != null) return 2

        for (i in 0..< moves.size)
        {
            if (moves[i] == null) return 0
            else if (moves[i] != null && i == moves.size - 1) return 4
        }

        return 0
    }
}