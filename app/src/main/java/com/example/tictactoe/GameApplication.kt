package com.example.tictactoe

import android.app.Application

/**
 * Pristup ku kontextu aplikácie obalený kontajnerom AppDataContainer.
 */
class GameApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this) // obalenie kontextu aplikácie do triedy [AppDataContainer]
    }
}