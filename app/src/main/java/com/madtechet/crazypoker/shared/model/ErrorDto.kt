package com.madtechet.crazypoker.shared.model

import com.google.gson.Gson

data class ErrorDto(
    var message: String,
    var code: String? = ""
)

object ErrorFactory {
    fun fromSocket(data: Any): ErrorDto {
        return Gson().fromJson(data.toString(), ErrorDto::class.java)
    }
}