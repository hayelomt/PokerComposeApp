package com.madtechet.crazypoker.modules.game.presentation.components.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madtechet.crazypoker.R
import com.madtechet.crazypoker.modules.game.model.PokerDrawable

@Composable
fun PokerCard(
    pokerDrawable: PokerDrawable,
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
    onTap: () -> Unit = {}
) {
    val cardWidth = if (expanded) 120.dp else 18.dp
    val imageId = if(expanded) pokerDrawable.cardId else pokerDrawable.cardSideId

    Box(
        modifier = modifier
            .height(180.dp)
            .width(cardWidth)
            .border(
                BorderStroke(1.dp, Color.Black)
            ).clickable { onTap() }
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCard() {
    PokerCard(
        PokerDrawable(
            R.drawable.club_1,
            R.drawable.club_1_side,
        ),
        expanded = true
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSideCard() {
    PokerCard(
        PokerDrawable(
            R.drawable.club_1,
            R.drawable.club_1_side,
        ),
        expanded = false
    )
}