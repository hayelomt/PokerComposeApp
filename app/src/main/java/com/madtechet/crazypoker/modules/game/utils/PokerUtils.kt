package com.madtechet.crazypoker.modules.game.utils

import com.madtechet.crazypoker.R
import com.madtechet.crazypoker.modules.game.model.Poker
import com.madtechet.crazypoker.modules.game.model.PokerDrawable

fun getPokerImageAsset(suite: String): Int {
    return when (suite) {
        "club" -> R.drawable.club
        "diamond" -> R.drawable.diamond
        "heart" -> R.drawable.heart
        else -> R.drawable.spade
    }
}

fun getPokerDrawable(poker: Poker): PokerDrawable {
    return when(poker.identifier) {
        "club_1" -> PokerDrawable(R.drawable.club_1, R.drawable.club_1_side)
        "club_2" -> PokerDrawable(R.drawable.club_2, R.drawable.club_2_side)
        "club_3" -> PokerDrawable(R.drawable.club_3, R.drawable.club_3_side)
        "club_4" -> PokerDrawable(R.drawable.club_4, R.drawable.club_4_side)
        "club_5" -> PokerDrawable(R.drawable.club_5, R.drawable.club_5_side)
        "club_6" -> PokerDrawable(R.drawable.club_6, R.drawable.club_6_side)
        "club_7" -> PokerDrawable(R.drawable.club_7, R.drawable.club_7_side)
        "club_8" -> PokerDrawable(R.drawable.club_8, R.drawable.club_8_side)
        "club_9" -> PokerDrawable(R.drawable.club_9, R.drawable.club_9_side)
        "club_10" -> PokerDrawable(R.drawable.club_10, R.drawable.club_10_side)
        "club_jack" -> PokerDrawable(R.drawable.club_jack, R.drawable.club_jack_side)
        "club_queen" -> PokerDrawable(R.drawable.club_queen, R.drawable.club_queen_side)
        "club_king" -> PokerDrawable(R.drawable.club_king, R.drawable.club_king_side)
        "diamond_1" -> PokerDrawable(R.drawable.diamond_1, R.drawable.diamond_1_side)
        "diamond_2" -> PokerDrawable(R.drawable.diamond_2, R.drawable.diamond_2_side)
        "diamond_3" -> PokerDrawable(R.drawable.diamond_3, R.drawable.diamond_3_side)
        "diamond_4" -> PokerDrawable(R.drawable.diamond_4, R.drawable.diamond_4_side)
        "diamond_5" -> PokerDrawable(R.drawable.diamond_5, R.drawable.diamond_5_side)
        "diamond_6" -> PokerDrawable(R.drawable.diamond_6, R.drawable.diamond_6_side)
        "diamond_7" -> PokerDrawable(R.drawable.diamond_7, R.drawable.diamond_7_side)
        "diamond_8" -> PokerDrawable(R.drawable.diamond_8, R.drawable.diamond_8_side)
        "diamond_9" -> PokerDrawable(R.drawable.diamond_9, R.drawable.diamond_9_side)
        "diamond_10" -> PokerDrawable(R.drawable.diamond_10, R.drawable.diamond_10_side)
        "diamond_jack" -> PokerDrawable(R.drawable.diamond_jack, R.drawable.diamond_jack_side)
        "diamond_queen" -> PokerDrawable(R.drawable.diamond_queen, R.drawable.diamond_queen_side)
        "diamond_king" -> PokerDrawable(R.drawable.diamond_king, R.drawable.diamond_king_side)
        "heart_1" -> PokerDrawable(R.drawable.heart_1, R.drawable.heart_1_side)
        "heart_2" -> PokerDrawable(R.drawable.heart_2, R.drawable.heart_2_side)
        "heart_3" -> PokerDrawable(R.drawable.heart_3, R.drawable.heart_3_side)
        "heart_4" -> PokerDrawable(R.drawable.heart_4, R.drawable.heart_4_side)
        "heart_5" -> PokerDrawable(R.drawable.heart_5, R.drawable.heart_5_side)
        "heart_6" -> PokerDrawable(R.drawable.heart_6, R.drawable.heart_6_side)
        "heart_7" -> PokerDrawable(R.drawable.heart_7, R.drawable.heart_7_side)
        "heart_8" -> PokerDrawable(R.drawable.heart_8, R.drawable.heart_8_side)
        "heart_9" -> PokerDrawable(R.drawable.heart_9, R.drawable.heart_9_side)
        "heart_10" -> PokerDrawable(R.drawable.heart_10, R.drawable.heart_10_side)
        "heart_jack" -> PokerDrawable(R.drawable.heart_jack, R.drawable.heart_jack_side)
        "heart_queen" -> PokerDrawable(R.drawable.heart_queen, R.drawable.heart_queen_side)
        "heart_king" -> PokerDrawable(R.drawable.heart_king, R.drawable.heart_king_side)
        "spade_1" -> PokerDrawable(R.drawable.spade_1, R.drawable.spade_1_side)
        "spade_2" -> PokerDrawable(R.drawable.spade_2, R.drawable.spade_2_side)
        "spade_3" -> PokerDrawable(R.drawable.spade_3, R.drawable.spade_3_side)
        "spade_4" -> PokerDrawable(R.drawable.spade_4, R.drawable.spade_4_side)
        "spade_5" -> PokerDrawable(R.drawable.spade_5, R.drawable.spade_5_side)
        "spade_6" -> PokerDrawable(R.drawable.spade_6, R.drawable.spade_6_side)
        "spade_7" -> PokerDrawable(R.drawable.spade_7, R.drawable.spade_7_side)
        "spade_8" -> PokerDrawable(R.drawable.spade_8, R.drawable.spade_8_side)
        "spade_9" -> PokerDrawable(R.drawable.spade_9, R.drawable.spade_9_side)
        "spade_10" -> PokerDrawable(R.drawable.spade_10, R.drawable.spade_10_side)
        "spade_jack" -> PokerDrawable(R.drawable.spade_jack, R.drawable.spade_jack_side)
        "spade_queen" -> PokerDrawable(R.drawable.spade_queen, R.drawable.spade_queen_side)
        "spade_king" -> PokerDrawable(R.drawable.spade_king, R.drawable.spade_king_side)
        "joker_1" -> PokerDrawable(R.drawable.joker_1, R.drawable.joker_1_side)
        "joker_2" -> PokerDrawable(R.drawable.joker_2, R.drawable.joker_2_side)
        "joker_3" -> PokerDrawable(R.drawable.joker_3, R.drawable.joker_3_side)
        else -> throw Exception("Unknown card identifier ${poker.identifier}")
    }
}