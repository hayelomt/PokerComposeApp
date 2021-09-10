package com.madtechet.crazypoker.modules.app.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home_screen")
    object Game: Screen("game_screen")
}
