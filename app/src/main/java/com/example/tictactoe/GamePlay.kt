package com.example.tictactoe

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.random.Random

/**
 * Hlavná trieda, ktorá spracuje informácie z používateľského rozhrania NewGameUI alebo MultiplayerNewGameWUI.
 * Tie si uloží a využíva ich pri používateľskom rozhraní GamePlayUI.
 * Po ukončení hry uloží informácie do databázy GamesDatabase cez repozitár ItemsRepository.
 */
class GamePlay(private val itemsRepository: ItemsRepository) : ViewModel()
{
    private val _uiState = MutableStateFlow(TTTState()) // Stav, ktorý si drží informácie popísané v [TTTState]
    val moves = mutableListOf<Boolean?>(null, null, null, null, null, null, null, null, null) // ťahy -> true = hráč 1, false = hráč 2, null = prázdny ťah
    val playerTurn = mutableStateOf(true) // kto je na rade
    var multiPlayerMode = mutableStateOf(true) // hráč proti hráčovi alebo hráč proti počítaču
    val gameOver = mutableStateOf(false) // koniec hry
    var difficulty = mutableIntStateOf(2) // obtiažnosť v prípade hre proti počítaču

    /**
     * Slúži na vytvorenie novej inštancie TTTState, resetovanie ťahov a premennej pre informáciu o konci hry.
     */
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

    /**
     *
     */
    fun playAgain()
    {
        if (!multiPlayerMode.value)
        {
            playerTurn.value = _uiState.value.player1Name != ""
        }

        for (i in 0..< moves.size)
        {
            if (moves[i] != null)
            {
                moves[i] = null
            }
        }
        addNumOfGamesPlayed()
        gameOver.value = false
    }

    /**
     * Zvýši počet odohraných hier o 1.
     */
    fun addNumOfGamesPlayed()
    {
        _uiState.value.numberOfGamesPlayed += 1
    }

    /**
     * Vráti počet odohraných hier.
     */
    fun getNumOfGamesPlayed() : Int
    {
        return _uiState.value.numberOfGamesPlayed
    }

    /**
     * Pohyb počítača v prípade hre proti počítaču.
     * 3 typy: Easy, Medium, Hard - podľa premennej difficulty.
     * Vráti pozíciu, na ktorú sa rozhodol použiť svoj ťah na zobrazenie obrázka.
     */
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

    /**
     * Ľahký mód obťiažnosti v hre proti počítaču.
     * Keď ide hráč vyhrať, zabráni mu v tom, ak nie vloží ťah na prvú voľnú pozíciu.
     * Vracia pozíciu svojho ťahu podľa tlačidiel.
     */
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

    /**
     * Stredný mód obťiažnosti v hre proti počítaču.
     * Keď ide hráč vyhrať, zabráni mu v tom, ak nie vloží ťah na náhodnú voľnú pozíciu.
     * Vracia pozíciu svojho ťahu podľa tlačidiel.
     */
    private fun moveAIMedium() : Int
    {
        val turn = !playerTurn.value
        val aiTurn = playerTurn.value
        var result = 9

        if (moves[0] == turn && moves[1] == turn && moves[2] == null) result = 2
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
        return result
    }

    /**
     * Ťažký mód obťiažnosti v hre proti počítaču.
     * Keď ide hráč vyhrať, zabráni mu v tom.
     * => Pokiaľ ide vyhrať počítač, tak to spraví.
     *   => Skontroluje 4 vrcholy, pokiaľ na jednom z nich už má svoj ťah, skontroluje ostatné, či sú prázdne, Vyberie náhodný z nich.
     *     => Skontroluje 4 stredné okraje, pokiaľ na jednom z nich už má svoj ťah, skontroluje ostatné, či sú prázdne, Vyberie náhodný z nich.
     *       => Dá na náhodnúi pozíciu.
     * Vracia pozíciu svojho ťahu podľa tlačidiel.
     */
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

    /**
     * Nastaví kto je na rade. Volá sa pri vytváraní hry v NewGameUI alebo MultiplayerNewGameUI.
     * Možnosť náhodného výberu.
     */
    fun setPlayerTurn(turn: Int)
    {
        when (turn)
        {
            0 -> playerTurn.value = chooseRandomTurn()
            1 -> playerTurn.value = true
            2 -> playerTurn.value = false
        }
    }

    /**
     * Náhodne vyberie, kto je na rade, pomocou triedy Random.
     */
    private fun chooseRandomTurn() : Boolean
    {
        return Random.nextBoolean()
    }

    /**
     * Nastaví ťah pomocou pozície.
     */
    fun setMove(move: Int)
    {
        moves[move] = playerTurn.value
    }

    /**
     * Ďalší je na ťahu - zmení kto je na ťahu na opačnú hodnotu.
     */
    fun goNext()
    {
        playerTurn.value = !playerTurn.value
    }

    /**
     * Vráti meno hráča 1.
     */
    fun getPlayer1Name() : String
    {
        return _uiState.value.player1Name
    }

    /**
     * Nastaví meno hráča 1.
     * Ak je nastavená hra proti počítaču a meno je prázdne, nastaví meno "AI".
     */
    fun setPlayer1Name(name: String)
    {
        if (!multiPlayerMode.value && name == "")
        {
            _uiState.value.player1Name = "AI"
        }
        else
        {
            _uiState.value.player1Name = name
        }
    }

    /**
     * Vráti meno hráča 2.
     */
    fun getPlayer2Name() : String
    {
        return _uiState.value.player2Name
    }

    /**
     * Nastaví meno hráča 2.
     * Ak je nastavená hra proti počítaču a meno je prázdne, nastaví meno "AI".
     */
    fun setPlayer2Name(name: String)
    {
        if (!multiPlayerMode.value && name == "")
        {
            _uiState.value.player2Name = "AI"
        }
        else
        {
            _uiState.value.player2Name = name
        }
    }

    /**
     * Vráti skóre hráča 1.
     */
    fun getPlayer1Score() : Int
    {
        return _uiState.value.player1Score
    }

    /**
     * Zvýši skóre hráča 1 o 1.
     */
    private fun addScoreToP1()
    {
        _uiState.value.player1Score += 1
    }

    /**
     * Vráti skóre hráča 2.
     */
    fun getPlayer2Score() : Int
    {
        return _uiState.value.player2Score
    }

    /**
     * Zvýši skóre hráča 2 o 1.
     */
    private fun addScoreToP2()
    {
        _uiState.value.player2Score += 1
    }

    /**
     * Skontroluje či je koniec hry a kto vyhral zavolaním metódy whoWon.
     * Podľa hodnoty pridá bod jednému s hráčou a oznaámi koniec hry zmenením hodnoty premennej [gameOver].
     * V prípade remízy sa body nepripíšu.
     */
    fun checkEnd()
    {
        val won = whoWon()
        if (won == 1) addScoreToP1() else if (won == 2) addScoreToP2()
        if (won > 0)
        {
            gameOver.value = true
        }
    }

    /**
     * Ak metóda checkGameOver vráti:
     *    1 => skontroluje hodnotu políčka 0
     *    2 => skontroluje hodnotu políčka 4
     *    3 => skontroluje hodnotu políčka 8
     * ak vyhral hráč 1 (true) vráti 1, ak hráč 2 (false) vráti 2.
     *    4 => vráti 3 - remíza
     *    0 => nikto ešte nevyhral
     */
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

    /**
     * Skontroluje všetky smery (8) a ich hodnoty.
     * Ak sa všetky 3 hodnoty vedľa seba rovnajú a nie sú prázdne, vráti hodnotu väčšiu ako 0.
     * Potom skontroluje či nenastala remíza - všetky políčka sú zaplnené ale nikto nevyhral.
     * 1, 2, 3 => niekto vyhral; vracia viac hodnôt, aby metóda whoWon vedela skontrolovať, kto vyhral.
     *            podľa vrátenej hodnoty vie skontrolovať príslučné políčko.
     * 4 => remíza
     * 0 => nie je koniec hry
     */
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

    /**
     * Získa si aktuálny dátum a čas, ktorý uloží do aktuálneho TTTState.
     * Uloží aktuálnu inštanciu TTTState do databázy cez ItemsRepository.
     */
    fun saveToDatabase(coroutineScope: CoroutineScope)
    {
        val dateTime = toDateAndTime(Date())
        val both = dateTime.split(' ')
        _uiState.value.date = both[0]
        _uiState.value.time = both[1]
        coroutineScope.launch{
            itemsRepository.insertItem(_uiState.value)
        }
    }

    /**
     * Vráti dátum a čas v správnom formáte "dd.MM.yyyy HH:mm:ss".
     */
    private fun toDateAndTime(date: Date) : String
    {
        val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
        return sdf.format(date)
    }
}