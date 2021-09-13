package com.madtechet.crazypoker.modules.game.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madtechet.crazypoker.modules.home.presentation.components.ActionButton

@Composable
fun StartGameContent(
    joinTag: String,
    onStartGame: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ActionButton(onClick = { onStartGame()}) {
            Text(text = "Start Game")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Join Tag: $joinTag")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStartGame() {
    StartGameContent("1eadd") {

    }
}