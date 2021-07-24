package com.example.pomodoro.domain.model

data class Timer(
    val id: Long,
    val time: Long,
    var currentTime: Long,
    var isStarted: Boolean,
)