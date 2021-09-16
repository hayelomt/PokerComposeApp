package com.madtechet.crazypoker.modules.game.presentation

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.madtechet.crazypoker.modules.app.container.ContainerScreen
import com.madtechet.crazypoker.modules.game.presentation.components.card.CardListing
import com.madtechet.crazypoker.modules.game.presentation.components.gameplay.Controls
import com.madtechet.crazypoker.modules.game.presentation.components.gameplay.DeckArea
import com.madtechet.crazypoker.modules.game.presentation.components.status.GameplayStatus
import com.madtechet.crazypoker.modules.game.viewmodel.GameContentDisplay
import com.madtechet.crazypoker.modules.game.viewmodel.GameStartViewModel
import com.madtechet.crazypoker.modules.game.viewmodel.GameViewModel
import com.madtechet.crazypoker.modules.game.viewmodel.PlayerViewModel

@Composable
fun GameScreen(
    navHostController: NavHostController,
    gameViewModel: GameViewModel,
    gameStartViewModel: GameStartViewModel,
    playerViewModel: PlayerViewModel,
) {
//    logIt("Came Screen")
    val skipEnabled = playerViewModel.isCurrentPlayer && playerViewModel.cardDrawn
    val gameplayState = playerViewModel.gameplayState
    val displayScreen = gameViewModel.displayScreen

    ContainerScreen(containerViewModel = gameViewModel) {
        when (displayScreen) {
            GameContentDisplay.Start -> {
                StartGameContent(
                    joinTag = gameStartViewModel.joinTag,
                    playersCount = gameStartViewModel.playersCount
                ) {
                    gameViewModel.startGame()
                }
            }
            GameContentDisplay.Waiting -> {
                WaitingStartContent()
            }
            GameContentDisplay.Finished -> {
                GameFinishedContent(
                    winner = gameViewModel.winner,
                    navHostController = navHostController
                )
            }
            else -> {
                GameContent(
                    gameplayStatus = {
                        GameplayStatus(
                            suite = gameplayState.currentSuite,
                            playersCount = gameplayState.playersCount,
                            currentPlayer = gameplayState.currentPlayer,
                            playDirection = gameplayState.playDirection
                        )
                    },
                    deckArea = { modifier ->
                        DeckArea(
                            modifier = modifier,
                            topCard = playerViewModel.topCard,
                            onBackCardTap = playerViewModel::drawCard
                        )
                    },
                    controls = {
                        Controls(
                            playEnabled = playerViewModel.isCurrentPlayer && playerViewModel.playEnabled,
                            onPlay = playerViewModel::playCards,
                            skipEnabled = skipEnabled,
                            onSkip = playerViewModel::skip,
                            asEnabled = playerViewModel.asEnabled,
                        )
                    },
                    cardListing = {
                        CardListing(
                            cards = playerViewModel.playerCards,
                            onCardTouch = { card ->
                                playerViewModel.toggleCardExpanded(card)
                            }
                        )
                    }
                )

            }
        }
    }

}

