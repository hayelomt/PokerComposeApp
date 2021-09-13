package com.madtechet.crazypoker.modules.game.model

import com.google.gson.Gson

data class GameStartedDto(
    var currentPlayerSocketId: String,
    var players: List<PlayerDto>,
    var direction: Int,
    var topCard: CardDto? = null
)

object GameStartedFactory {
    fun fromSocket(data: Any): GameStartedDto {
        return Gson().fromJson(data.toString(), GameStartedDto::class.java)
    }
}