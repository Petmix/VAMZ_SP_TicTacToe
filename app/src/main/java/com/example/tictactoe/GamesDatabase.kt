package com.example.tictactoe

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Trieda Games databázy ako singleton objekt.
 * Singleton => vždy existuje len jedna inštancia.
 */
@Database(entities = [TTTState::class], version = 1, exportSchema = false)
abstract class GamesDatabase : RoomDatabase() {

    abstract fun gamesDao(): GamesDao // cez ňu máme prístup k Dao, s ktorým vieme pracovať s databázou

    companion object {
        @Volatile
        private var Instance: GamesDatabase? = null

        fun getDatabase(context: Context): GamesDatabase {
            // Ak už je inštancia databázy, vráť ju, inak vytvor novú inštanciu databázy.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, GamesDatabase::class.java, "games_database")
                    .build().also { Instance = it }
            }
        }
    }
}