package com.madtechet.crazypoker.modules.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import com.google.gson.Gson
import com.madtechet.crazypoker.modules.app.navigation.AppNavigation
import com.madtechet.crazypoker.modules.game.presentation.components.gameplay.Controls
import com.madtechet.crazypoker.modules.game.presentation.components.status.CurrentSuite
import com.madtechet.crazypoker.modules.game.presentation.components.status.Players
import com.madtechet.crazypoker.shared.network.SocketHandler
import com.madtechet.crazypoker.shared.ui.theme.CrazyPokerTheme
import com.madtechet.crazypoker.shared.utils.logIt
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SocketHandler.setSocket()
        SocketHandler.establishConnection()

//        val socket = SocketHandler.getSocket()
//        socket.emit("get-me")
//        socket.on("me") {args ->
//            val user = UserFactory.fromSocket(args[0])
//            logIt("Me $user")
//        }
//        socket.emit("post-me", User("potter", listOf("ron", "hermione"), 17).toSocket())

        setContent {
            CrazyPokerTheme {
                AppNavigation()
            }
        }
    }
}