package com.madtechet.crazypoker.modules.game.presentation.components.status

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.madtechet.crazypoker.R
import com.madtechet.crazypoker.modules.game.utils.getPokerImageAsset


@Composable
fun CurrentSuite(suite: String = "") {
    Box(
        modifier = Modifier
            .height(70.dp)
            .width(55.dp)
    ) {
        Column(verticalArrangement = Arrangement.SpaceBetween) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                if (suite.isNotEmpty()) {
                    Image(
                        painter = painterResource(id = getPokerImageAsset(suite)),
                        contentDescription = "Suite Icon",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Block, contentDescription = "Suite Icon",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(40.dp)
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    "Current",
                    style = MaterialTheme.typography.subtitle1.copy(fontSize = 9.sp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Suite",
                    style = MaterialTheme.typography.subtitle1.copy(fontSize = 9.sp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCurrentSuite() {
    CurrentSuite("club")
}

@Preview(showBackground = true)
@Composable
fun PreviewCurrentSuiteHeart() {
    CurrentSuite("heart")
}

@Preview(showBackground = true)
@Composable
fun PreviewCurrentSuiteNull() {
    CurrentSuite()
}