package com.madtechet.crazypoker.modules.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.madtechet.crazypoker.modules.app.navigation.AppNavigation
import com.madtechet.crazypoker.shared.network.SocketHandler
import com.madtechet.crazypoker.shared.ui.theme.CrazyPokerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SocketHandler.setSocket()
        SocketHandler.establishConnection()

        setContent {
            CrazyPokerTheme {
                AppNavigation()
            }
        }
    }
}

