package com.madtechet.crazypoker.modules.home.viewmodel

import androidx.compose.runtime.*
import com.madtechet.crazypoker.modules.app.container.ContainerViewModel
import com.madtechet.crazypoker.modules.home.model.GameCreatedFactory
import com.madtechet.crazypoker.shared.model.ErrorFactory
import com.madtechet.crazypoker.shared.utils.SnackTypes
import com.madtechet.crazypoker.shared.utils.SocketEvents
import com.madtechet.crazypoker.shared.utils.logIt
import dagger.hilt.android.lifecycle.HiltViewModel
import io.socket.client.Socket
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val socket: Socket
): ContainerViewModel() {
    var navigateGame by mutableStateOf(false)
        private set
    var gameId by mutableStateOf<String?>(null)
        private set

    init {
        socket.on(SocketEvents.createdGame) { args ->
            logIt("Game Created ${args[0]}")
            val gameCreated = GameCreatedFactory.fromSocket(args[0])
            gameId = gameCreated.id
            navigateGame = true
        }
        socket.on(SocketEvents.joinedGame) { args ->
            gameId = args[0] as String
            navigateGame = true
        }
        socket.on(SocketEvents.errorEvent) { args ->
            logIt("Error ${args[0]}")
            val errorMsg = ErrorFactory.fromSocket(args[0])
            showMessage(errorMsg.message)
        }

//        socket.emit(SocketEvents.createGame, "titan")
    }

    fun clearNavigate() {
        navigateGame = false
    }

    fun createGame(username: String) {
//        logIt("Create game $username")
        socket.emit(SocketEvents.createGame, username)
    }

    fun joinGame(joinTag: String, username: String) {
//        logIt("Join Game $joinTag - $username")
        socket.emit(SocketEvents.joinGame, joinTag, username)
    }
}
