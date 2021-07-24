package com.example.pomodoro.data.timer

import com.example.pomodoro.domain.model.Timer
import kotlinx.coroutines.flow.Flow

interface TimerRepository {

    suspend fun addTimer(time: Long)

    suspend fun deleteTimer(id: Long)

    suspend fun startTimer(id: Long)

    suspend fun stopTimer(id: Long)

    fun timersFlow(): Flow<List<Timer>>

    fun endTimerFlow(): Flow<Timer?>
}