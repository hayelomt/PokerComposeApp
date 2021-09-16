package com.madtechet.crazypoker.modules.game.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import com.madtechet.crazypoker.modules.app.container.ContainerViewModel
import com.madtechet.crazypoker.modules.game.model.*
import com.madtechet.crazypoker.shared.model.ErrorFactory
import com.madtechet.crazypoker.shared.utils.SocketEvents
import com.madtechet.crazypoker.shared.utils.logIt
import dagger.hilt.android.lifecycle.HiltViewModel
import io.socket.client.Socket
import javax.inject.Inject


enum class GameContentDisplay { Start, Waiting, Gameplay, Finished }

@HiltViewModel
class GameViewModel @Inject constructor(
    private val socket: Socket,
    savedStateHandle: SavedStateHandle
) : ContainerViewModel() {
    private val gameId = savedStateHandle.get<String>("gameId")

    var displayScreen by mutableStateOf(GameContentDisplay.Waiting)
    var winner by mutableStateOf("")
        private set

    init {
        logIt("GameId: $gameId")
        socket.on(SocketEvents.errorEvent) { args ->
            logIt("Error ${args[0]}")
            val errorMsg = ErrorFactory.fromSocket(args[0])
            showMessage(errorMsg.message)
        }

        socket.on(SocketEvents.infoGame) { args ->
            val gameInfo = GameInfoFactory.fromSocket(args[0])
            displayScreen =
                if (gameInfo.isCreator) GameContentDisplay.Start else GameContentDisplay.Waiting
        }

        socket.on(SocketEvents.playerJoined) { args ->
            val player = PlayerJoinedFactory.fromSocket(args[0])
            showMessage(player.player)
        }

        socket.on(SocketEvents.startedGame) { _ ->
            logIt("Started Game ")
            displayScreen = GameContentDisplay.Gameplay
        }

        socket.on(SocketEvents.finishedGame) { args ->
            logIt("Game Finished ${args[0]}")
            val finishedDto = GameFinishedFactory.fromSocket(args[0])
            winner = finishedDto.winner
            displayScreen = GameContentDisplay.Finished
        }

        socket.on(SocketEvents.cardLeft) { args ->
            logIt("Card LEft ${args[0]}")
            val player = args[0] as String
            showMessage("$player has single card left")
        }

        // Remove
//        socket.emit(SocketEvents.startGame, gameId)
    }

    fun startGame() {
        socket.emit(SocketEvents.startGame, gameId)
    }
}