package com.example.tictactoe

import kotlinx.coroutines.flow.Flow

class OfflineItemsRepository(private val gamesDao: GamesDao) : ItemsRepository {
    override fun getAllItemsStream(): Flow<List<TTTState>> = gamesDao.getAllGames()
    override suspend fun insertItem(item: TTTState) = gamesDao.insertGame(item)
    override suspend fun deleteItem(item: TTTState) = gamesDao.deleteGame(item)
}