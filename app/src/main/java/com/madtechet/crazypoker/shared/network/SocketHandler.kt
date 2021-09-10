package com.madtechet.crazypoker.shared.network

import com.madtechet.crazypoker.shared.utils.Constants
import com.madtechet.crazypoker.shared.utils.logErr
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object SocketHandler {
    lateinit var mSocket: Socket

    @Synchronized
    fun setSocket() {
        try {
            mSocket = IO.socket(Constants.SERVER_URL)
        } catch (e: URISyntaxException) {
            logErr("Socket err $e")
        } catch (e: Exception) {
            logErr("Error $e")
        }
    }

    @Synchronized
    fun getSocket(): Socket = mSocket

    @Synchronized
    fun establishConnection() {
        mSocket.connect()
    }

    @Synchronized
    fun closeConnection() {
        mSocket.disconnect()
    }

}