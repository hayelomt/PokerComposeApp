package com.madtechet.crazypoker.modules.game.model

import com.google.gson.Gson

data class GameInfoDto(
    var joinTag: String,
    var isCreator: Boolean,
    var gameStatus: String,
    var playersCount: Int
)

object GameInfoFactory {
    fun fromSocket(data: Any): GameInfoDto {
        return Gson().fromJson(data.toString(), GameInfoDto::class.java)
    }
}