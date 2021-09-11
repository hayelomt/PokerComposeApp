package com.madtechet.crazypoker.modules.game.presentation.components.card

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madtechet.crazypoker.R

@Composable
fun PokerCardBack(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.card_back),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .width(127.dp)
            .height(185.dp)
            .border(
                BorderStroke(1.dp, Color.Black)
            )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCardBack() {
    PokerCardBack()
}