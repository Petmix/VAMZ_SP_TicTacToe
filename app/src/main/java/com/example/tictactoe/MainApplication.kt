package com.example.tictactoe

import android.app.Application

class MainApplication : Application()
{
    val database: GamesDatabase by lazy { GamesDatabase.getDatabase(this) }
}