package com.madtechet.crazypoker.shared.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madtechet.crazypoker.shared.utils.SnackTypes

fun getIcon(snackType: SnackTypes): ImageVector {
    return when(snackType) {
        SnackTypes.Message -> Icons.Outlined.Notifications
        SnackTypes.Info -> Icons.Outlined.Info
        SnackTypes.Error -> Icons.Outlined.Warning
    }
}

@Composable
fun getBgColor(snackType: SnackTypes): Color {
    return when(snackType) {
        SnackTypes.Error -> MaterialTheme.colors.error.copy(alpha = 0.65f)
        else -> SnackbarDefaults.backgroundColor.copy(alpha = 0.8f)
    }
}

@Composable
fun AlertSnack(message: String, snackType: SnackTypes = SnackTypes.Message) {
    Snackbar(backgroundColor = getBgColor(snackType), shape = RoundedCornerShape(5.dp)) {
        Row(horizontalArrangement = Arrangement.Start) {
            Icon(imageVector = getIcon(snackType), contentDescription = "Snack Type")
            Spacer(modifier = Modifier.width(10.dp))
            Text(message)
        }
    }
}

/** Previews */
@Preview(showBackground = true)
@Composable
fun PreviewAlertSnackMessage() {
    AlertSnack(
        message = "Some Success",
        snackType = SnackTypes.Message
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAlertSnackInfo() {
    AlertSnack(
        message = "Info message",
        snackType = SnackTypes.Info
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAlertSnackError() {
    AlertSnack(
        message = "Something Went wrong",
        snackType = SnackTypes.Error
    )
}