package com.example.tictactoe

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [TTTState::class], version = 1, exportSchema = false)
abstract class GamesDatabase : RoomDatabase() {

    abstract fun gamesDao(): GamesDao

    companion object {
        @Volatile
        private var Instance: GamesDatabase? = null

        fun getDatabase(context: Context): GamesDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, GamesDatabase::class.java, "games_database")
                    .build().also { Instance = it }
            }
        }
    }
}