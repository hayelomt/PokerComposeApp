package com.madtechet.crazypoker.modules.game.model

import com.google.gson.Gson

data class CardDto(
    var identifier: String,
    var suite: String,
    var value: String,
    var title: String,
) {
    fun toPoker(): Poker = Poker(
        identifier = this.identifier,
        suite = this.suite,
        value = this.value,
        title = this.title,
        expanded = false,
    )
}

object CardDtoFactory {
    fun fromSocket(data: Any): List<CardDto> {
        return Gson().fromJson(data.toString(), Array<CardDto>::class.java).toList()
    }

    fun fromSocketSingle(data: Any): CardDto {
        return Gson().fromJson(data.toString(), CardDto::class.java)
    }
}
