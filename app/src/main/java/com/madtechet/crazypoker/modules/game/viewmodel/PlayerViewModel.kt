package com.madtechet.crazypoker.modules.game.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.madtechet.crazypoker.modules.game.model.CardDtoFactory
import com.madtechet.crazypoker.modules.game.model.CurrentPlayerFactory
import com.madtechet.crazypoker.modules.game.model.GameStartedFactory
import com.madtechet.crazypoker.modules.game.model.Poker
import com.madtechet.crazypoker.shared.utils.SocketEvents
import com.madtechet.crazypoker.shared.utils.logIt
import dagger.hilt.android.lifecycle.HiltViewModel
import io.socket.client.Socket
import javax.inject.Inject

//val cardList = listOf(
//    Poker(title = "Club 1", identifier = "club_1", suite = "club", value = "1", expanded = false),
//    Poker(title = "Club 2", identifier = "club_2", suite = "club", value = "2"),
//    Poker(title = "Club 3", identifier = "club_3", suite = "club", value = "3"),
//    Poker(title = "Joker 2", identifier = "joker_2", suite = "joker", value = "2"),
//    Poker(title = "Heart King", identifier = "heart_king", suite = "heart", value = "king"),
//    Poker(title = "Diamond 10", identifier = "diamond_8", suite = "diamond", value = "8")
//)

data class GameplayState(
    val currentPlayer: String = "",
    val currentSuite: String = "",
    val playersCount: Int = 0,
    val playDirection: Int = 1,
)

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val socket: Socket,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val gameId = savedStateHandle.get<String>("gameId")

    var gameplayState by mutableStateOf(GameplayState())
        private set
    var cardDrawn by mutableStateOf(false)
        private set
    var topCard by mutableStateOf<Poker?>(null)
        private set
    var playerCards = mutableStateListOf<Poker>()
        private set
    var isCurrentPlayer by mutableStateOf(false)

    val playEnabled: Boolean get() = isCurrentPlayer && playerCards.find { it.expanded } != null
    val asEnabled: Boolean get() = isCurrentPlayer && playerCards.find { it.expanded && it.value == "8" } != null

    init {
        gameplayState = GameplayState("", "", 0, 1)

        socket.on(SocketEvents.startedGame) { args ->
            val startedGame = GameStartedFactory.fromSocket(args[0])
            isCurrentPlayer = startedGame.currentPlayerSocketId == socket.id()
            gameplayState = gameplayState.copy(
                playDirection = startedGame.direction,
                playersCount = startedGame.players.size
            )
        }

        socket.on(SocketEvents.playerCards) { args ->
            val playerHand = CardDtoFactory.fromSocket(args[0])
            playerCards.clear()
            playerCards.addAll(playerHand.map { it.toPoker() })
        }

        socket.on(SocketEvents.cardTop) { args ->
            logIt("Card TOP ${args[0]}")
            val card = CardDtoFactory.fromSocketSingle(args[0])
            topCard = card.toPoker()
        }

        socket.on(SocketEvents.cardCurrentSuite) { args ->
            logIt("Current Card${args[0] as String}")
            gameplayState = gameplayState.copy(currentSuite = args[0] as String)
        }

        socket.on(SocketEvents.playerCurrent) { args ->
            logIt("Current Player")
            val player = CurrentPlayerFactory.fromSocket(args[0])
            gameplayState = gameplayState.copy(currentPlayer = player.username)
            isCurrentPlayer = player.socketId == socket.id()
            if (isCurrentPlayer) {
                cardDrawn = false
            }
        }

        socket.on(SocketEvents.playerCount) { args ->
            logIt("Player COunt")
            gameplayState = gameplayState.copy(playersCount = args[0] as Int)
        }

        socket.on(SocketEvents.cardDirection) { args ->
            logIt("Card Direction")
            gameplayState = gameplayState.copy(playDirection = args[0] as Int)
        }
    }

    fun toggleCardExpanded(poker: Poker) {
        val cardIndex = playerCards.indexOf(poker)
        playerCards[cardIndex] = poker.copy(expanded = !poker.expanded)
    }

    fun playCards(chosenSuite: String) {
        cardDrawn = false
        val drawnCards = playerCards.filter { it.expanded }.map { it.toSocket() }
        socket.emit(SocketEvents.cardMoveGame, gameId, drawnCards, chosenSuite)
//        logIt("Play Cards, Suite: $chosenSuite as: $asEnabled  $drawnCards")
    }

    fun drawCard() {
//        logIt("Draw card")
        if (!cardDrawn) {
            cardDrawn = true
            socket.emit(SocketEvents.cardDrawGame, gameId)
        }
    }

    fun skip() {
//        logIt("Skip Turn")
        cardDrawn = false
        socket.emit(SocketEvents.cardMoveGame, gameId, listOf<String>(), "")
    }
}