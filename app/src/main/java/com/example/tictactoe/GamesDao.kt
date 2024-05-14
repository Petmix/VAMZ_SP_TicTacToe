package com.example.tictactoe

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Database access object to access the Games database
 */
@Dao
interface GamesDao
{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGame(game: TTTState)

    @Delete
    suspend fun deleteGame(game: TTTState)

    @Query("SELECT * from games ORDER BY id DESC")
    fun getAllGames(): Flow<List<TTTState>>
}