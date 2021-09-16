package com.madtechet.crazypoker.shared.utils

object Constants {
    const val TAG = "CrazyPoker"
    const val SERVER_URL = "http://10.0.2.2:4000"
}

object SocketEvents {
    const val createdGame = "game:created"
    const val createGame = "game:create"
    const val joinedGame = "game:joined"
    const val joinGame = "game:join"
    const val playerJoined = "player:joined"
    const val startedGame = "game:started"
    const val startGame = "game:start"
    const val finishedGame = "game:finished"

    const val infoGame = "game:info"
    const val cardMoveGame = "game:cardMove"
    const val cardDrawGame = "game:cardDraw"

    const val playerCards = "player:cards"
    const val playerCurrent = "player:current"
    const val playerCount = "player:count"

    const val cardCurrentSuite = "card:current-suite"
    const val cardDirection = "card:direction"
    const val cardTop = "card:top"
    const val cardLeft = "card:left"

    const val errorEvent = "io:error"
}

enum class SnackTypes {
    Error,
    Message,
    Info,
}