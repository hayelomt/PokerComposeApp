package com.madtechet.crazypoker.modules.app.container

import androidx.compose.material.*
import androidx.compose.runtime.*

@Composable
fun ContainerScreen(containerViewModel: ContainerViewModel, content: @Composable () -> Unit = {}) {
    val scaffoldState = rememberScaffoldState()

    if (containerViewModel.isSnackBarShowing) {
        LaunchedEffect(key1 = containerViewModel.isSnackBarShowing) {
            scaffoldState.snackbarHostState.showSnackbar(containerViewModel.snackbarMessage)
            containerViewModel.dismissSnackbar()
        }
    }

    Scaffold(scaffoldState = scaffoldState) {
        content()
    }
}