package com.madtechet.crazypoker.modules.game.model

import com.google.gson.Gson

data class GameFinishedDto(
    var winner: String
)

object GameFinishedFactory {
    fun fromSocket(data: Any): GameFinishedDto {
        return Gson().fromJson(data.toString(), GameFinishedDto::class.java)
    }
}