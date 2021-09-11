package com.madtechet.crazypoker.modules.home.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.madtechet.crazypoker.shared.utils.logIt
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {
    var navigateGame by mutableStateOf(false)
        private set
    var homeError by mutableStateOf<String?>(null)
        private set

    fun clearError() {
        homeError = null
    }

    fun createGame(username: String) {
        logIt("Create game $username")
        navigateGame = true
        homeError = "Invalid Username"
    }

    fun joinGame(joinTag: String, username: String) {
        logIt("Join Game $joinTag - $username")
    }
}
