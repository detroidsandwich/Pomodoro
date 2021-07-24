package com.example.pomodoro.data.timer.cache

import com.example.pomodoro.domain.model.Timer
import kotlinx.coroutines.flow.Flow

interface TimerDataStore {

    suspend fun addTimer(time: Long)

    suspend fun deleteTimer(id: Long)

    suspend fun startTimer(id: Long)

    suspend fun stopTimer(id: Long)

    val timersFlow: Flow<List<Timer>>

    val endTimerFlow: Flow<Timer>
}