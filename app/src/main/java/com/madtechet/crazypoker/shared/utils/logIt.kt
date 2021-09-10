package com.madtechet.crazypoker.shared.utils

import android.util.Log

fun logIt(logStuff: String) {
    Log.d(Constants.TAG, logStuff)
}

fun logErr(errorLog: String) {
    Log.d(Constants.TAG, "Error:> $errorLog")
}