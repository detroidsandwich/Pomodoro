package com.example.pomodoro.ui.view.adapter

import android.graphics.drawable.AnimationDrawable
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.pomodoro.R
import com.example.pomodoro.databinding.ItemTimerBinding
import com.example.pomodoro.ui.common.displayTime

class TimerViewHolder(private val binding: ItemTimerBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(listener: TimerViewHolderListener, timer: TimerItem) = with(binding) {
        progress.setPeriod(timer.time)

        textTimer.text = timer.currentTime.toString()

        switchButton(timer.isStarted)
        animateRec(timer.isStarted)
        updateTimer(timer)
        endTimer(timer)

        buttonPlay.setOnClickListener {
            listener.onStart(timer.id)
        }

        buttonStop.setOnClickListener {
            listener.onStop(timer.id)
        }

        buttonDelete.setOnClickListener {
            listener.onDelete(timer.id)
        }
    }

    private fun switchButton(isStart: Boolean) = with(binding) {
        buttonPlay.isVisible = !isStart
        buttonStop.isVisible = isStart
    }

    fun bind(listener: TimerViewHolderListener, timer: TimerItem, payload: Payload) =
        when (payload) {
            Payload.START_TIMER -> startTimer(timer)
            Payload.UPDATE_TIMER -> updateTimer(timer)
            Payload.STOP_TIMER -> stopTimer(timer)
            Payload.END_TIMER -> {
                stopTimer(timer)
                endTimer(timer)
            }
        }

    private fun startTimer(timer: TimerItem) = with(binding) {
        switchButton(timer.isStarted)
        animateRec(timer.isStarted)
        updateTimer(timer)
    }

    private fun updateTimer(timer: TimerItem) = with(binding) {
        textTimer.text = timer.currentTime.displayTime()
        progress.setCurrent(timer.time - timer.currentTime)
    }

    private fun stopTimer(timer: TimerItem) = with(binding) {
        switchButton(timer.isStarted)
        animateRec(timer.isStarted)
        updateTimer(timer)
    }

    private fun animateRec(isStart: Boolean) {
        if (isStart) {
            binding.imageRec.isInvisible = false
            (binding.imageRec.background as? AnimationDrawable)?.start()
        } else {
            binding.imageRec.isInvisible = true
            (binding.imageRec.background as? AnimationDrawable)?.stop()
        }
    }

    private fun endTimer(timer: TimerItem) {
        val color = if (timer.currentTime <= 0L) {
            ContextCompat.getColor(itemView.context, R.color.red)
        } else {
            ContextCompat.getColor(itemView.context, R.color.white)
        }
        binding.card.setCardBackgroundColor(color)
    }
}

enum class Payload {
    START_TIMER, UPDATE_TIMER, STOP_TIMER, END_TIMER
}

interface TimerViewHolderListener {
    fun onStart(id: Long)
    fun onStop(id: Long)
    fun onDelete(id: Long)
}