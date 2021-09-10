package com.madtechet.crazypoker.modules.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.madtechet.crazypoker.modules.home.presentation.components.ButtonContainer
import com.madtechet.crazypoker.shared.ui.components.AlertSnack
import com.madtechet.crazypoker.shared.utils.SnackTypes
import com.madtechet.crazypoker.shared.utils.logIt
import kotlinx.coroutines.launch

@Composable
fun HomeContent(
    createGame: (String) -> Unit,
    joinGame: (String, String) -> Unit,
) {
    logIt("Home Content")
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var snackType by remember { mutableStateOf(SnackTypes.Message) }

    val showMessage = { message: String, type: SnackTypes ->
        snackType = type
        scope.launch {
            scaffoldState.snackbarHostState.showSnackbar(message)
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
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ButtonContainer(
                createGame = createGame,
                joinGame = joinGame,
                showMessage = showMessage
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeContent() {
    HomeContent(createGame = {}, joinGame = { _, _ -> })
}