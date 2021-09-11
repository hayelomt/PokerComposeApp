package com.madtechet.crazypoker.modules.game.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.madtechet.crazypoker.modules.game.model.Poker
import com.madtechet.crazypoker.modules.game.presentation.components.card.CardListing
import com.madtechet.crazypoker.modules.game.presentation.components.card.PokerCard
import com.madtechet.crazypoker.modules.game.presentation.components.gameplay.Controls
import com.madtechet.crazypoker.modules.game.presentation.components.gameplay.DeckArea
import com.madtechet.crazypoker.modules.game.presentation.components.status.GameplayStatus
import com.madtechet.crazypoker.modules.game.utils.getPokerDrawable
import com.madtechet.crazypoker.modules.game.viewmodel.GameViewModel
import com.madtechet.crazypoker.shared.utils.logIt

@Composable
fun GameScreen(gameViewModel: GameViewModel) {
//    logIt("Came Screen")
    val skipEnabled by gameViewModel.cardDrawn
    val playerState by gameViewModel.playerState

    GameContent(
        gameplayStatus = {
            GameplayStatus(
                suite = playerState.currentSuite,
                playersCount = playerState.playersCount,
                currentPlayer = playerState.currentPlayer,
                playDirection = playerState.playDirection
            )
        },
        deckArea = { modifier ->
            DeckArea(
                modifier = modifier,
                topCard = Poker(
                    title = "1 of Heart",
                    identifier = "heart_1",
                    suite = "heart",
                    value = "1",
                    expanded = true
                ),
                onBackCardTap = gameViewModel::drawCard
            )
        },
        controls = {
            Controls(
                playEnabled = gameViewModel.playEnabled,
                onPlay = gameViewModel::playCards,
                skipEnabled =  skipEnabled,
                onSkip = gameViewModel::skip,
                asEnabled = gameViewModel.asEnabled,
            )
        },
        cardListing = {
            CardListing(
                cards = gameViewModel.playerCards,
                onCardTouch = { card ->
                    gameViewModel.toggleCardExpanded(card)
                }
            )
        }
    )

}

