package com.madtechet.crazypoker.modules.game.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.madtechet.crazypoker.modules.app.container.ContainerViewModel
import com.madtechet.crazypoker.modules.game.model.GameInfoFactory
import com.madtechet.crazypoker.modules.game.model.PlayerJoinedFactory
import com.madtechet.crazypoker.shared.utils.SocketEvents
import com.madtechet.crazypoker.shared.utils.logIt
import dagger.hilt.android.lifecycle.HiltViewModel
import io.socket.client.Socket
import javax.inject.Inject

@HiltViewModel
class GameStartViewModel @Inject constructor(
    private val socket: Socket,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val gameId = savedStateHandle.get<String>("gameId")

    var joinTag by mutableStateOf("")
        private set
    var playersCount by mutableStateOf(0)
        private set

    init {

        socket.emit(SocketEvents.infoGame, gameId)

        socket.on(SocketEvents.infoGame) { args ->
            logIt("Info Game Start")
            val gameInfo = GameInfoFactory.fromSocket(args[0])
            joinTag = gameInfo.joinTag
            playersCount = gameInfo.playersCount
        }

        socket.on(SocketEvents.playerJoined) { args ->
            logIt("Player Joined ${args[0]}")
            val player = PlayerJoinedFactory.fromSocket(args[0])
            playersCount = player.playersCount
        }

    }
}