package com.example.tictactoe

import kotlinx.coroutines.flow.Flow

/**
 * Trieda pre prácu s GamesDao.
 * Má uloženú informácie, ktoré funckie volájú z Dao ktoré metódy.
 * Využíva ho ItemsRepository rozhranie na prácu s databázou.
 */
class OfflineItemsRepository(private val gamesDao: GamesDao) : ItemsRepository {
    override fun getAllItemsStream(): Flow<List<TTTState>> = gamesDao.getAllGames()
    override suspend fun insertItem(item: TTTState) = gamesDao.insertGame(item)
    override suspend fun deleteItem(item: TTTState) = gamesDao.deleteGame(item)
}