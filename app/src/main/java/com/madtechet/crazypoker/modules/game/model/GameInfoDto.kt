package com.madtechet.crazypoker.modules.game.model

import com.google.gson.Gson

enum class GameStatus(status: String) {
    Pending("PENDING"),
    Started("STARTED"),
    Finished("FINISHED"),
    Abandoned("ABANDONED")
}

data class GameInfoDto(
    var joinTag: String,
    var isCreator: Boolean,
    var status: GameStatus,
    var direction: Int,
)

object GameInfoFactory {
    fun fromSocket(data: Any): GameInfoDto {
        return Gson().fromJson(data.toString(), GameInfoDto::class.java)
    }
}