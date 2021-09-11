package com.madtechet.crazypoker.modules.game.model

import androidx.annotation.DrawableRes

data class Poker(
    val title: String,
    val identifier: String,
    val suite: String,
    val value: String,
    var expanded: Boolean = false
)

data class PokerDrawable(
    @DrawableRes val cardId: Int,
    @DrawableRes val cardSideId: Int,
)