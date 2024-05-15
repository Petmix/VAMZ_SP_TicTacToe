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
     * Drží si stav hier uložených v databáze.
     * List položiek je zobratý pomocou ItemsRepository a zmapovaný na dátovú triedu DatabaseState
     */
    val scoreState: StateFlow<DatabaseState> =
        itemsRepository.getAllItemsStream().map { DatabaseState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DatabaseState()
            )

    /**
     * Na zastavenie metódy SharingStarted.WhileSubscribed po 5000 milisekundách (5 sekúnd).
     */
    companion object
    {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * Dátová trieda pre list, do ktorého sa vložia všetky TTTState vybrané z databázy.
 */
data class DatabaseState(val itemList: List<TTTState> = listOf())