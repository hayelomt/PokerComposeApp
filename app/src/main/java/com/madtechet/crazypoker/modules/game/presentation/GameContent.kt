package com.madtechet.crazypoker.modules.game.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.madtechet.crazypoker.modules.game.model.Poker
import com.madtechet.crazypoker.modules.game.presentation.components.card.CardListing
import com.madtechet.crazypoker.modules.game.presentation.components.gameplay.Controls
import com.madtechet.crazypoker.modules.game.presentation.components.gameplay.DeckArea
import com.madtechet.crazypoker.modules.game.presentation.components.status.GameplayStatus

@Composable
fun GameContent(
    gameplayStatus: @Composable () -> Unit,
    deckArea: @Composable (Modifier) -> Unit,
    controls: @Composable () -> Unit,
    cardListing: @Composable () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        gameplayStatus()
        deckArea(
            Modifier
                .weight(1f)
                .fillMaxWidth())
        Column {
            controls()
            cardListing()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGameContent() {
    GameContent(
        gameplayStatus = {
            GameplayStatus(suite = "heart", playersCount = 4, currentPlayer = "titan")
        },
        deckArea = { modifier ->
//            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                DeckArea(
                    modifier = modifier,
                    topCard = Poker(
                        title = "1 of Heart",
                        identifier = "heart_1",
                        suite = "heart",
                        value = "1",
                        expanded = true
                    ),
                )
//            }
        },
        controls = {
            Controls(
                true,
                {},
                false,
                {},
                true
            )
        },
        cardListing = {
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
                )
            ) {}
        }
    )
}