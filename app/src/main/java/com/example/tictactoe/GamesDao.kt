package com.example.tictactoe

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Databázový objekt na prístup ku Games databáze.
 */
@Dao
interface GamesDao
{
    /**
     * Funkcionalita pre vloženie inštancie TTTState do Games databázy.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGame(game: TTTState)

    /**
     * Funkcionalita pre odstránenie inštancie TTTState z Games databázy.
     */
    @Delete
    suspend fun deleteGame(game: TTTState)

    /**
     * Funkcionalita pre získanie všetkých inštancií TTTState z Games databázy.
     */
    @Query("SELECT * from games ORDER BY id DESC")
    fun getAllGames(): Flow<List<TTTState>>
}