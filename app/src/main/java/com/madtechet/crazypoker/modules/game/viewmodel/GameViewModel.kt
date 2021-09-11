package com.madtechet.crazypoker.modules.game.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.madtechet.crazypoker.modules.game.model.Poker
import com.madtechet.crazypoker.shared.utils.logIt
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

val cardList = listOf(
    Poker(title = "Club 1", identifier = "club_1", suite = "club", value = "1", expanded = false),
    Poker(title = "Club 2", identifier = "club_2", suite = "club", value = "2"),
    Poker(title = "Club 3", identifier = "club_3", suite = "club", value = "3"),
    Poker(title = "Joker 2", identifier = "joker_2", suite = "joker", value = "2"),
    Poker(title = "Heart King", identifier = "heart_king", suite = "heart", value = "king"),
    Poker(title = "Diamond 10", identifier = "diamond_8", suite = "diamond", value = "8")
)

data class PlayersState(
    val currentPlayer: String = "",
    val currentSuite: String = "",
    val playersCount: Int = 0,
    val playDirection: Int = 1
)

@HiltViewModel
class GameViewModel @Inject constructor() : ViewModel() {
    var cardDrawn = mutableStateOf(false)
        private set
    var playerCards = mutableStateListOf<Poker>()
        private set
    var playerState = mutableStateOf(PlayersState())

    val playEnabled: Boolean get() = playerCards.find { it.expanded } != null
    val asEnabled: Boolean get() = playerCards.find { it.expanded && it.value == "8" } != null

    init {
        playerCards.addAll(cardList)
        playerState.value = PlayersState("mel", "club", 5, -1)
    }

    fun toggleCardExpanded(poker: Poker) {
        val cardIndex = playerCards.indexOf(poker)
        playerCards[cardIndex] = poker.copy(expanded = !poker.expanded)
    }

    fun drawCard() {
        logIt("Draw card")
        cardDrawn.value = true
    }

    fun playCards(chosenSuite: String) {
        val drawnCards = playerCards.filter { it.expanded }
        logIt("Play Cards, Suite: $chosenSuite as: $asEnabled  $drawnCards")
    }

    fun skip() {
        logIt("Skip Turn")
    }
}