package com.madtechet.crazypoker.modules.game.presentation

import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.madtechet.crazypoker.modules.game.model.Poker
import com.madtechet.crazypoker.modules.game.presentation.components.card.CardListing
import com.madtechet.crazypoker.modules.game.presentation.components.gameplay.Controls
import com.madtechet.crazypoker.modules.game.presentation.components.gameplay.DeckArea
import com.madtechet.crazypoker.modules.game.presentation.components.status.GameplayStatus
import com.madtechet.crazypoker.modules.game.viewmodel.GameContentDisplay
import com.madtechet.crazypoker.modules.game.viewmodel.GameViewModel
import com.madtechet.crazypoker.shared.ui.components.AlertSnack
import com.madtechet.crazypoker.shared.utils.SnackTypes
import com.madtechet.crazypoker.shared.utils.logIt
import kotlinx.coroutines.launch

@Composable
fun GameScreen(gameViewModel: GameViewModel, navHostController: NavHostController) {
//    logIt("Came Screen")
    val skipEnabled = gameViewModel.cardDrawn
    val playerState = gameViewModel.playerState
    val displayScreen = gameViewModel.displayScreen

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var snackType by remember { mutableStateOf(SnackTypes.Message) }

    val showMessage = { message: String, type: SnackTypes ->
        scope.launch {
            logIt("Snack Type: $type")
            snackType = type
//            scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
            scaffoldState.snackbarHostState.showSnackbar(message)
        }
    }

    gameViewModel.homeError?.let { errMsg ->
        if (errMsg.isNotBlank()) {
            showMessage(errMsg, SnackTypes.Error)
//            showMessage("errMsg", SnackTypes.Message)
            gameViewModel.clearError()
        }
    }

    gameViewModel.homeMessage?.let { msg ->
        if (msg.isNotBlank()) {
            showMessage(msg, SnackTypes.Message)
            gameViewModel.clearMessage()
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(it) { data ->
                AlertSnack(message = data.message, snackType = snackType)
            }
        }
    ) {
        when (displayScreen) {
            GameContentDisplay.Start -> {
                StartGameContent(joinTag = gameViewModel.joinTag) {
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
                            suite = playerState.currentSuite,
                            playersCount = playerState.playersCount,
                            currentPlayer = playerState.currentPlayer,
                            playDirection = playerState.playDirection
                        )
                    },
                    deckArea = { modifier ->
                        DeckArea(
                            modifier = modifier,
                            topCard = gameViewModel.topCard,
//                            topCard = Poker(
//                                title = "1 of Heart",
//                                identifier = "heart_1",
//                                suite = "heart",
//                                value = "1",
//                                expanded = true
//                            ),
                            onBackCardTap = gameViewModel::drawCard
                        )
                    },
                    controls = {
                        Controls(
                            playEnabled = gameViewModel.playEnabled,
                            onPlay = gameViewModel::playCards,
                            skipEnabled = skipEnabled,
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
        }
    }

}

