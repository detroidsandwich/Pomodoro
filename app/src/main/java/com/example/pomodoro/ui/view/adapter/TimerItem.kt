package com.example.pomodoro.ui.view.adapter

import com.example.pomodoro.domain.model.Timer

data class TimerItem(
    val id: Long,
    val time: Long,
    val currentTime: Long,
    val isStarted: Boolean,
) {
    fun isEnd() = currentTime <= 0
}

fun Timer.toTimerItem() = TimerItem(
    id = id,
    time = time,
    currentTime = currentTime,
    isStarted = isStarted
)
