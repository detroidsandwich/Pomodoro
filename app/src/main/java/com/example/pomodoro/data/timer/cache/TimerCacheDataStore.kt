package com.example.pomodoro.data.timer.cache

import com.example.pomodoro.domain.model.Timer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class TimerCacheDataStore : TimerDataStore {

    private val items: MutableList<Timer> = mutableListOf()

    override val timersFlow = MutableSharedFlow<List<Timer>>(1)

    override val endTimerFlow = MutableSharedFlow<Timer>()

    override suspend fun addTimer(time: Long) {
        items.add(Timer(System.currentTimeMillis(), time, time, false))
        timersFlow.emit(items)
    }

    override suspend fun deleteTimer(id: Long) {
        val index = items.indexOfFirst { it.id == id }
        items.removeAt(index)
        timersFlow.emit(items)
    }

    override suspend fun startTimer(id: Long) {
        items.forEach {
            if (it.id == id) {
                it.isStarted = true
                timer(it)
            } else {
                it.isStarted = false
            }
        }
        timersFlow.emit(items)
    }

    override suspend fun stopTimer(id: Long) {
        timerJob?.cancel()
        items.first { it.id == id }.isStarted = false
        timersFlow.emit(items)
    }

    private var timerJob: Job? = null
    suspend fun timer(timer: Timer) {
        timerJob?.cancel()
        timerJob = GlobalScope.launch {
            for (t in timer.currentTime downTo 0) {
                timer.currentTime = t
                timersFlow.emit(items)
                delay(1000L)
            }
            timer.isStarted = false
            timersFlow.emit(items)
        }
    }
}