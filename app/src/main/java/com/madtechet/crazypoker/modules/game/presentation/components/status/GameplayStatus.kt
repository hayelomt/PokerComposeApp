package com.madtechet.crazypoker.modules.game.presentation.components.status

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GameplayStatus(
    suite: String,
    playersCount: Int,
    currentPlayer: String,
    playDirection: Int = 1,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Players(playersCount = playersCount, currentPlayer = currentPlayer, playDirection)
        CurrentSuite(suite)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGameplayStatus() {
    GameplayStatus(suite = "heart", playersCount = 4, currentPlayer = "mozart")
}