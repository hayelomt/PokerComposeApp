package com.madtechet.crazypoker.modules.home

import androidx.lifecycle.ViewModel
import com.madtechet.crazypoker.shared.utils.logIt
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    fun createGame(username: String) {
        logIt("Create game $username")
    }

    fun joinGame(joinTag: String, username: String) {
        logIt("Join Game $joinTag - $username")
    }
}
