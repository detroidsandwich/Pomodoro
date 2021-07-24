package com.example.pomodoro.data

interface DataStore {

    suspend fun isEmpty(): Boolean

}