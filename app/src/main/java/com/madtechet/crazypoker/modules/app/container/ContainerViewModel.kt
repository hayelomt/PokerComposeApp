package com.madtechet.crazypoker.modules.app.container

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.madtechet.crazypoker.shared.utils.SnackTypes
import com.madtechet.crazypoker.shared.utils.logIt
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class ContainerViewModel @Inject constructor() : ViewModel(){
    var snackbarMessage by mutableStateOf("")
        protected set
    var isSnackBarShowing: Boolean by mutableStateOf(false)
        private set

    fun showMessage(message: String?) {
//        logIt("Show Messag VM $message")
        message?.let { msg ->
//            logIt("Show Message $msg")
            if (msg.isNotBlank()) {
                snackbarMessage = msg;
                isSnackBarShowing = true
            }
        }
    }

    fun dismissSnackbar() {
        isSnackBarShowing = false
    }
}