package com.madtechet.crazypoker.modules.home.presentation.components.join


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun JoinGameDialog(
    onConfirm: (String, String) -> Unit,
    onDismiss: () -> Unit
) {
    var joinTag by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var usernameFieldTouched by remember { mutableStateOf(false) }
    val usernameIsInValid = username.length < 3 || username.length > 6

    AlertDialog(
        title = {
            Text(text = "Join Game")
        },
        text = {
            Column {
                OutlinedTextField(
                    value = joinTag,
                    label = { Text("Join Tag") },
                    onValueChange = {
                        joinTag = it
                    },
                    placeholder = { Text("Enter Join tag") },
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = username,
                    label = { Text("Username") },
                    onValueChange = {
                        username = it
                        usernameFieldTouched = true
                    },
                    placeholder = { Text("Enter username(3-6 characters)") },
                    isError = usernameFieldTouched && usernameIsInValid
                )
                if (usernameFieldTouched && usernameIsInValid) {
                    Text(
                        textAlign = TextAlign.Start,
                        text = "Username must be between 3-6 characters",
                        style = MaterialTheme.typography.caption.copy(color = Color.Red),
                    )
                }
            }
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = { onConfirm(joinTag, username) },
                enabled = joinTag.isNotBlank() && usernameFieldTouched && !usernameIsInValid
            ) {
                Text("Join")
            }
        }
    )
}