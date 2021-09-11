package com.madtechet.crazypoker.modules.home.presentation.components.join

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import com.madtechet.crazypoker.modules.home.presentation.components.ActionButton

@Composable
fun JoinGame(onJoin: (String, String) -> Unit) {
    var showJoinDialog by remember { mutableStateOf(false) }

    ActionButton(onClick = { showJoinDialog = true }) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Join")
            Text(text = "Game")
        }
    }

    if (showJoinDialog) {
        JoinGameDialog(
            onConfirm = { joinTag, username ->
                onJoin(joinTag, username)
                showJoinDialog = false
            },
            onDismiss = { showJoinDialog = false },
        )
    }
}