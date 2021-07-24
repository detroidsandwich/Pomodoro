package com.example.pomodoro.ui.common

const val START_TIME = "00:00:00"

fun Long.displayTime(): String {
    if (this <= 0L) {
        return START_TIME
    }
    val h = this / 3600
    val m = this  % 3600 / 60
    val s = this  % 60
    return "${displaySlot(h)}:${displaySlot(m)}:${displaySlot(s)}"
}

private fun displaySlot(count: Long): String {
    return if (count / 10L > 0) {
        "$count"
    } else {
        "0$count"
    }
}