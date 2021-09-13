package com.madtechet.crazypoker.modules.home.model

import com.google.gson.Gson

data class GameCreatedDto(
    var id: String,
    var joinTag: String,
)

object GameCreatedFactory {
    fun fromSocket(data: Any): GameCreatedDto {
        return Gson().fromJson(data.toString(), GameCreatedDto::class.java)
    }
}