package com.example.pomodoro.data.common

import com.example.pomodoro.data.DataStore

suspend fun <T : DataStore> T.retrieveDataStore(network: T, forceNetwork: Boolean = false): T =
    if (this.isEmpty() || forceNetwork) {
        network
    } else {
        this
    }
