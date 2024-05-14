package com.example.tictactoe

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

/**
 * Provides Factory to create instance of ViewModel for the entire Tic Tac Toe app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            GamePlay(inventoryApplication().container.itemsRepository)
        }
        initializer {
            ScoreBoardViewModel(inventoryApplication().container.itemsRepository)
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [GameApplication].
 */
fun CreationExtras.inventoryApplication(): GameApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GameApplication)