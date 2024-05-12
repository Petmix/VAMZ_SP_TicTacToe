package com.example.tictactoe

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [TTTState] from a given data source.
 */
interface ItemsRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllItemsStream(): Flow<List<TTTState>>

    /**
     * Insert item in the data source
     */
    suspend fun insertItem(item: TTTState)

    /**
     * Delete item from the data source
     */
    suspend fun deleteItem(item: TTTState)

}