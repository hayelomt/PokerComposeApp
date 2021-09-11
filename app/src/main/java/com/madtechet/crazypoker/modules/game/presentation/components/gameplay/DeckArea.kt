package com.madtechet.crazypoker.modules.game.presentation.components.gameplay

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.madtechet.crazypoker.modules.game.model.Poker
import com.madtechet.crazypoker.modules.game.presentation.components.card.PokerCard
import com.madtechet.crazypoker.modules.game.presentation.components.card.PokerCardBack
import com.madtechet.crazypoker.modules.game.utils.getPokerDrawable

@Composable
fun DeckArea(
    modifier: Modifier = Modifier,
    topCard: Poker? = null,
) {
    Row(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(0.8f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        PokerCardBack()
        topCard?.let {
            PokerCard(pokerDrawable = getPokerDrawable(topCard), expanded = true)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDeckArea() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        DeckArea(
            topCard = Poker(
                title = "1 of Heart",
                identifier = "heart_1",
                suite = "heart",
                value = "1",
                expanded = true
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDeckAreaEmpty() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        DeckArea()
    }
}