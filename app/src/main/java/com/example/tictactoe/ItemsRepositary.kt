package com.example.tictactoe

import kotlinx.coroutines.flow.Flow

/**
 * Repozitár ponúka funckie pre vloženie, vymazanie alebo získanie inštancií TTTState z daného dátového zdroju.
 */
interface ItemsRepository {
    /**
     * Získa všetky položky typz TTTState z daného dátového zdroju.
     */
    fun getAllItemsStream(): Flow<List<TTTState>>

    /**
     * Vloží položku typu TTTState do daného dátového zdroju.
     */
    suspend fun insertItem(item: TTTState)

    /**
     * Vymaže položku typu TTTState z daného dátového zdroju.
     */
    suspend fun deleteItem(item: TTTState)

}