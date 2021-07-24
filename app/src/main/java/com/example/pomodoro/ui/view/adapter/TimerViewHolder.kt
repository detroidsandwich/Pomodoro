package com.example.pomodoro.ui.view.adapter

import android.graphics.drawable.AnimationDrawable
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
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

        buttonAction.isEnabled = !timer.isEnd()

        buttonAction.setOnClickListener {
            if (!timer.isStarted) {
                listener.onStart(timer.id)
            } else {
                listener.onStop(timer.id)
            }
        }

        buttonDelete.setOnClickListener {
            listener.onDelete(timer.id)
        }
    }

    private fun switchButton(isStart: Boolean) = with(binding) {
        val drawable = if (!isStart) {
            ContextCompat.getDrawable(itemView.context, R.drawable.ic_baseline_play_arrow_24)
        } else {
            ContextCompat.getDrawable(itemView.context, R.drawable.ic_baseline_stop_24)
        }
        buttonAction.setImageDrawable(drawable)
    }

    fun bind(listener: TimerViewHolderListener, timer: TimerItem, payload: Payload) =
        when (payload) {
            Payload.UPDATE_TIMER -> updateTimer(timer)
        }

    private fun updateTimer(timer: TimerItem) = with(binding) {
        textTimer.text = timer.currentTime.displayTime()
        progress.setCurrent(timer.time - timer.currentTime)
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
    UPDATE_TIMER
}

interface TimerViewHolderListener {
    fun onStart(id: Long)
    fun onStop(id: Long)
    fun onDelete(id: Long)
}