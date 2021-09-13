package com.madtechet.crazypoker.modules.home.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.madtechet.crazypoker.modules.home.model.GameCreatedFactory
import com.madtechet.crazypoker.shared.model.ErrorFactory
import com.madtechet.crazypoker.shared.utils.SocketEvents
import com.madtechet.crazypoker.shared.utils.logIt
import dagger.hilt.android.lifecycle.HiltViewModel
import io.socket.client.Socket
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val socket: Socket
): ViewModel() {
    var navigateGame by mutableStateOf(false)
        private set
    var homeError by mutableStateOf<String?>(null)
        private set
    var gameId by mutableStateOf<String?>(null)
        private set

    init {
        socket.on(SocketEvents.gameCreated) { args ->
            val gameCreated = GameCreatedFactory.fromSocket(args[0])
//            logIt("Game Created ${args[0]} $gameCreated")
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
            homeError = errorMsg.message
        }
//        socket.emit(SocketEvents.createGame, "titan")
    }

    fun clearError() {
        homeError = null
    }

    fun clearNavigate() {
        navigateGame = false
    }

    fun createGame(username: String) {
        logIt("Create game $username")
        socket.emit(SocketEvents.createGame, username)
    }

    fun joinGame(joinTag: String, username: String) {
        logIt("Join Game $joinTag - $username")
        socket.emit(SocketEvents.joinGame, joinTag, username)
    }
}
