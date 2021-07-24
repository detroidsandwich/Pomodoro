package com.example.pomodoro.ui.view.adapter

import com.example.pomodoro.domain.model.Timer

data class TimerItem(
    val id: Long,
    val time: Long,
    val currentTime: Long,
    val isStarted: Boolean,
)

fun Timer.toTimerItem() = TimerItem(
    id = id,
    time = time,
    currentTime = currentTime,
    isStarted = isStarted
)
