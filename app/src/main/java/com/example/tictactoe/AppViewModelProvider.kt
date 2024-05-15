package com.example.tictactoe

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

/**
 * Objekt, ktorý dáva Factory na vytvorenie inštancie ViewModel pre celú Tic Tac Toe aplikáciu.
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            GamePlay(inventoryApplication().container.itemsRepository) // inicializátor pre triedu [GamePlay] ako [ViewModel], ktorý dostal ako parameter [ItemsRepositary].
        }
        initializer {
            ScoreBoardViewModel(inventoryApplication().container.itemsRepository) // inicializátor pre triedu [ScoreBoardViewModel] ako [ViewModel], ktorý dostal ako parameter [ItemsRepositary].
        }
    }
}

/**
 * Rozširovacia funkcia na prejdenie objektu Application, ktorá vráti inštanciu GameApplication.
 */
fun CreationExtras.inventoryApplication(): GameApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GameApplication)