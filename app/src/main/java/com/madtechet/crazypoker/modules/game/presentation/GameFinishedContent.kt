package com.madtechet.crazypoker.modules.game.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.madtechet.crazypoker.modules.app.navigation.Screen

@Composable
fun GameFinishedContent(winner: String, navHostController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Winner $winner")
        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = { navHostController.popBackStack() }) {
            Text(text = "Done")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGameFinished() {
    GameFinishedContent(winner = "Titan", navHostController = NavHostController(LocalContext.current))
}