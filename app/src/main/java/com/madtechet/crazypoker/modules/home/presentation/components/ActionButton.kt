package com.madtechet.crazypoker.modules.home.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ActionButton(
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.size(100.dp),
        shape = CircleShape,
        border = BorderStroke(1.dp, MaterialTheme.colors.primary),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colors.primary),
        elevation = ButtonDefaults.elevation(8.dp)
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewActionButton() {
    ActionButton(onClick = { /*TODO*/ }) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Create")
            Text(text = "Game")
        }
    }
}