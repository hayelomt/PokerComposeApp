package com.madtechet.crazypoker.modules.game.presentation.components.status

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Players(
    playersCount: Int,
    currentPlayer: String,
    playDirection: Int = 1,
) {
    Box(
        modifier = Modifier
            .height(70.dp)
            .width(60.dp)
            .border(BorderStroke(2.dp, SolidColor(Color.Black)))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 5.dp)
            ) {
                Text("$playersCount")
            }
            Box(modifier = Modifier.weight(1f)) {
                Text(currentPlayer, style = MaterialTheme.typography.body1)
            }
            Icon(
                imageVector = if (playDirection == 1) Icons.Outlined.ArrowForward else Icons.Outlined.ArrowBack,
                contentDescription = "Play Direction",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPlayers() {
    Players(playersCount = 3, currentPlayer = "titanh", playDirection = -1)
}