package com.madtechet.crazypoker.modules.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.madtechet.crazypoker.modules.home.HomeViewModel
import com.madtechet.crazypoker.shared.ui.components.AlertSnack
import com.madtechet.crazypoker.shared.utils.SnackTypes
import com.madtechet.crazypoker.shared.utils.logIt
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController, homeVewModel: HomeViewModel) {
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

    // VM Listeners
    homeVewModel.navigateGame.let { goToGame ->
        if (goToGame) {
            logIt("Navigate to game screen $goToGame")
        }
    }

    homeVewModel.homeError.let { errMsg ->
        if (errMsg != null && errMsg.isNotBlank()) {
            showMessage(errMsg, SnackTypes.Error)
            showMessage("errMsg", SnackTypes.Message)
            homeVewModel.clearError()
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
        HomeContent(
            createGame = homeVewModel::createGame,
            joinGame = homeVewModel::joinGame,
            showMessage = showMessage
        )
    }
}