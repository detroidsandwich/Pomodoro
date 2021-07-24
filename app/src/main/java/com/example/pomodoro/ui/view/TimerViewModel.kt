package com.example.pomodoro.ui.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pomodoro.data.timer.TimerRepository
import com.example.pomodoro.ui.view.adapter.toTimerItem
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class TimerViewModel(
    private val timerRepository: TimerRepository
) : ViewModel() {

    val timersFlow = timerRepository.timersFlow().map { list ->
        list.map { it.toTimerItem() }
    }

    val endTimerFlow = timerRepository.endTimerFlow()

    fun addTimer(time: Long) {
        viewModelScope.launch {
            timerRepository.addTimer(time)
        }
    }

    fun deleteTimer(id: Long) {
        viewModelScope.launch {
            timerRepository.deleteTimer(id)
        }
    }

    fun startTimer(id: Long) {
        viewModelScope.launch {
            timerRepository.startTimer(id)
        }
    }

    fun stopTimer(id: Long) {
        viewModelScope.launch {
            timerRepository.stopTimer(id)
        }
    }
}