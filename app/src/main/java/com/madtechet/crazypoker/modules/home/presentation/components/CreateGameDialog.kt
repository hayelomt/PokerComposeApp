package com.madtechet.crazypoker.modules.home.presentation.components


import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CreateGameDialog(
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var username by remember { mutableStateOf("titan") }
    var fieldTouched by remember { mutableStateOf(false) }
    val isInValid = username.length < 3 || username.length > 6

    AlertDialog(
        title = {
            Text(text = "Create Game")
        },
        text = {
            Column {
                OutlinedTextField(
                    value = username,
                    label = { Text("Username") },
                    onValueChange = {
                        username = it
                        fieldTouched = true
                    },
                    placeholder = { Text("Enter username(3-6 characters)")},
                    isError = fieldTouched && isInValid
                )
                if (fieldTouched && isInValid) {
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
            Button(onClick = { onConfirm(username) }, enabled = fieldTouched && !isInValid) {
                Text("Create")
            }
        }
    )
}