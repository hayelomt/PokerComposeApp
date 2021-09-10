package com.madtechet.crazypoker.modules.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.madtechet.crazypoker.modules.home.HomeViewModel

@Composable
fun HomeScreen(navController: NavHostController, homeVewModel: HomeViewModel) {
    // VM Listeners
    HomeContent(
        createGame = homeVewModel::createGame,
        joinGame = homeVewModel::joinGame
    )
}