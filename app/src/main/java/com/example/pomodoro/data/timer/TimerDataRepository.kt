package com.example.pomodoro.data.timer

import com.example.pomodoro.data.timer.cache.TimerDataStore
import com.example.pomodoro.domain.model.Timer
import kotlinx.coroutines.flow.Flow

class TimerDataRepository(
    private val cache: TimerDataStore
): TimerRepository {

    override suspend fun addTimer(time: Long) {
        cache.addTimer(time)
    }

    override suspend fun deleteTimer(id: Long) {
        cache.deleteTimer(id)
    }

    override suspend fun startTimer(id: Long) {
        cache.startTimer(id)
    }

    override suspend fun stopTimer(id: Long) {
        cache.stopTimer(id)
    }

    override fun timersFlow(): Flow<List<Timer>> {
        return cache.timersFlow
    }

    override fun endTimerFlow(): Flow<Timer?> {
        return cache.endTimerFlow
    }
}