package com.madtechet.crazypoker.modules.home.presentation

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.madtechet.crazypoker.modules.app.container.ContainerScreen
import com.madtechet.crazypoker.modules.app.navigation.Screen
import com.madtechet.crazypoker.modules.home.viewmodel.HomeViewModel
import com.madtechet.crazypoker.shared.utils.SnackTypes
import com.madtechet.crazypoker.shared.utils.logIt

@Composable
fun HomeScreen(navController: NavHostController, homeVewModel: HomeViewModel) {
    // VM Listeners
    homeVewModel.navigateGame.let { goToGame ->
        if (goToGame) {
            logIt("Navigate to game screen $goToGame")
            navController.navigate(Screen.Game.withArgs(homeVewModel.gameId!!))
            homeVewModel.clearNavigate()
        }
    }

    LaunchedEffect(key1 = true) {
//        navController.navigate(Screen.Game.withArgs("6142fcefcdc73fbab66b1756"))
    }

    ContainerScreen(containerViewModel = homeVewModel) {
        HomeContent(
            createGame = homeVewModel::createGame,
            joinGame = homeVewModel::joinGame,
            showMessage = homeVewModel::showMessage
        )
    }

}