package com.example.tictactoe

import android.content.Context

/**
 * Kontajner aplikácie na prístup k inštancii ItemsRepository.
 */
interface AppContainer
{
    val itemsRepository: ItemsRepository
}

/**
 * AppContainer implementácia, ktorá dodáva inštanciu OfflineItemsRepository
 */
class AppDataContainer(private val context: Context) : AppContainer
{
    override val itemsRepository: ItemsRepository by lazy {
        OfflineItemsRepository(GamesDatabase.getDatabase(context).gamesDao()) // získanie Dao z databázy, ktorá je daná ako parameter pri vytvorení inštancie [OfflineItemsRepository]
    }
}