package com.madtechet.crazypoker.modules.game.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.madtechet.crazypoker.modules.game.model.*
import com.madtechet.crazypoker.shared.model.ErrorFactory
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

data class PlayersState(
    val currentPlayer: String = "",
    val currentSuite: String = "",
    val playersCount: Int = 0,
    val playDirection: Int = 1,
)

enum class GameContentDisplay { Start, Waiting, Gameplay, Finished }

@HiltViewModel
class GameViewModel @Inject constructor(
    private val socket: Socket,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val gameId = savedStateHandle.get<String>("gameId")

    var homeMessage by mutableStateOf<String?>(null)
        private set
    var homeError by mutableStateOf<String?>(null)
        private set
    var cardDrawn by mutableStateOf(false)
        private set
    var playerCards = mutableStateListOf<Poker>()
        private set
    var topCard by mutableStateOf<Poker?>(null)
    var joinTag by mutableStateOf("")
    var displayScreen by mutableStateOf(GameContentDisplay.Waiting)
    var playerState by mutableStateOf(PlayersState())
    var isCurrentPlayer by mutableStateOf(false)
    var winner by mutableStateOf("")

    val playEnabled: Boolean get() = isCurrentPlayer && playerCards.find { it.expanded } != null
    val asEnabled: Boolean get() = isCurrentPlayer && playerCards.find { it.expanded && it.value == "8" } != null

    init {
        logIt("Params: $gameId")
//        playerCards.addAll(cardList)
        playerState = PlayersState("", "", 0, 1)

        socket.emit(SocketEvents.infoGame, gameId)

        socket.on(SocketEvents.errorEvent) { args ->
            logIt("Error ${args[0]}")
            val errorMsg = ErrorFactory.fromSocket(args[0])
            homeError = errorMsg.message
        }

        socket.on(SocketEvents.infoGame) { args ->
            val gameInfo = GameInfoFactory.fromSocket(args[0])
            joinTag = gameInfo.joinTag
            displayScreen =
                if (gameInfo.isCreator) GameContentDisplay.Start else GameContentDisplay.Waiting
        }

        socket.on(SocketEvents.startedGame) { args ->
            val startedGame = GameStartedFactory.fromSocket(args[0])
            logIt("Started Game ")
            displayScreen = GameContentDisplay.Gameplay
            isCurrentPlayer = startedGame.currentPlayerSocketId == socket.id()

            playerState = playerState.copy(
                playDirection = startedGame.direction,
                playersCount = startedGame.players.size
            )
        }

        socket.on(SocketEvents.playerCards) { args ->
            val playerHand = CardDtoFactory.fromSocket(args[0])
            playerCards.clear()
            playerCards.addAll(playerHand.map { it.toPoker() })
            logIt("Player Cards $playerHand")
        }

        socket.on(SocketEvents.playerCurrent) { args ->
            logIt("Current Player ${args[0]}")
            val player = CurrentPlayerFactory.fromSocket(args[0])
            playerState = playerState.copy(currentPlayer = player.username)
            isCurrentPlayer = player.socketId == socket.id()
            cardDrawn = false
        }

        socket.on(SocketEvents.cardCurrent) { args ->
            playerState = playerState.copy(currentSuite = args[0] as String)
//            logIt("Current Card${args[0] as String}")
        }

        socket.on(SocketEvents.cardDirection) { args ->
            playerState = playerState.copy(playDirection = args[0] as Int)
        }

        socket.on(SocketEvents.cardTop) { args ->
//            logIt("Card TOP ${args[0]}")
            val card = CardDtoFactory.fromSocketSingle(args[0])
            topCard = card.toPoker()
        }

        socket.on(SocketEvents.finishedGame) { args ->
            logIt("Game Finished ${args[0]}")
            displayScreen = GameContentDisplay.Finished
        }

        socket.on(SocketEvents.cardLeft) { args ->
            logIt("Card LEft ${args[0]}")
            val player = args[0] as String
            homeMessage = "$player has single card left"
        }

        // Remove
//        socket.emit(SocketEvents.startGame, gameId)
//        logIt("Id ${socket.id()}")
    }

    fun clearError() {
        homeError = null
    }

    fun clearMessage() {
        homeMessage = null
    }

    fun toggleCardExpanded(poker: Poker) {
        val cardIndex = playerCards.indexOf(poker)
        playerCards[cardIndex] = poker.copy(expanded = !poker.expanded)
    }

    fun drawCard() {
        logIt("Draw card")
        cardDrawn = true
        socket.emit(SocketEvents.cardDrawGame, gameId)
    }

    fun playCards(chosenSuite: String) {
        val drawnCards = playerCards.filter { it.expanded }.map { it.toSocket() }
        socket.emit(SocketEvents.moveGame, gameId, drawnCards, "club")
        logIt("Play Cards, Suite: $chosenSuite as: $asEnabled  $drawnCards")
    }

    fun skip() {
        logIt("Skip Turn")
        socket.emit(SocketEvents.moveGame, gameId, listOf<String>(), "")
    }

    fun startGame() {
        logIt("Start game")
        socket.emit(SocketEvents.startGame, gameId)
    }
}