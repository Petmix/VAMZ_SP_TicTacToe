package com.example.tictactoe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ScoreBoardViewModel(itemsRepository: ItemsRepository) : ViewModel()
{
    /**
     * Holds score state. The list of items are retrieved from [ItemsRepository] and mapped to
     * [DatabaseState]
     */
    val scoreState: StateFlow<DatabaseState> =
        itemsRepository.getAllItemsStream().map { DatabaseState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DatabaseState()
            )

    companion object
    {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class DatabaseState(val itemList: List<TTTState> = listOf())