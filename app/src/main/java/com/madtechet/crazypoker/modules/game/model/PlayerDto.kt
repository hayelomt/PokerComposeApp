package com.madtechet.crazypoker.modules.game.model

import com.google.gson.Gson

data class PlayerDto(
    var username: String,
    var isStarter: Boolean = false
)

object PlayerDtoFactory {
    fun fromSocket(data: Any): PlayerDto {
        return Gson().fromJson(data.toString(), PlayerDto::class.java)
    }
}

data class CurrentPlayerDto(
    var username: String,
    var socketId: String,
)

object CurrentPlayerFactory {
    fun fromSocket(data: Any): CurrentPlayerDto {
        return Gson().fromJson(data.toString(), CurrentPlayerDto::class.java)
    }
}