package com.madtechet.crazypoker.modules.home.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.madtechet.crazypoker.modules.home.presentation.components.create.CreateGame
import com.madtechet.crazypoker.modules.home.presentation.components.join.JoinGame
import com.madtechet.crazypoker.shared.utils.SnackTypes
import kotlinx.coroutines.Job

@Composable
fun ButtonContainer(
    createGame: (String) -> Unit,
    joinGame: (String, String) -> Unit,
    showMessage: (String, SnackTypes) -> Job
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        CreateGame(
            onCreateGame = {
                createGame(it)
//                showMessage("Submitted, awaiting result", SnackTypes.Message)
            }
        )
        JoinGame(
            onJoin = { joinTag, username ->
                joinGame(joinTag, username)
                showMessage("Joining game...", SnackTypes.Message)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewButtonContainer() {
    ButtonContainer(
        {},
        {_, _ -> },
        showMessage = { _, _ ->
            Job()
        }
    )
}