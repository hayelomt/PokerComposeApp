package com.madtechet.crazypoker.modules.game.presentation.components.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madtechet.crazypoker.modules.game.model.Poker
import com.madtechet.crazypoker.modules.game.utils.getPokerDrawable

@Composable
fun CardListing(
    cards: List<Poker>,
    onCardTouch: (Poker) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 15.dp)
            .border(BorderStroke(1.dp, Color.Black)),
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(cards) { card ->
                Box(modifier = Modifier
                    .fillMaxHeight()
                    .clickable { onCardTouch(card) }) {
                    PokerCard(
                        pokerDrawable = getPokerDrawable(card),
                        expanded = card.expanded,
                        modifier = Modifier.align(
                            if (card.expanded) Alignment.TopCenter else Alignment.BottomCenter
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardListing() {
    CardListing(
        cards = listOf(
            Poker(
                title = "Club 1",
                identifier = "club_1",
                suite = "club",
                value = "1",
                expanded = true
            ),
            Poker(
                title = "Club 2",
                identifier = "club_2",
                suite = "club",
                value = "2",
                expanded = false
            ),
            Poker(
                title = "Club 3",
                identifier = "club_3",
                suite = "club",
                value = "3",
                expanded = false
            ),
            Poker(
                title = "Joker 2",
                identifier = "joker_2",
                suite = "joker",
                value = "2",
                expanded = true
            ),
            Poker(
                title = "Heart King",
                identifier = "heart_king",
                suite = "heart",
                value = "king",
                expanded = false
            ),
            Poker(
                title = "Diamond 10",
                identifier = "diamond_10",
                suite = "diamond",
                value = "10",
                expanded = false
            )
        )
    ) {}
}
