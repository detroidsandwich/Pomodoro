package com.example.pomodoro.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.pomodoro.databinding.ItemTimerBinding

class RecyclerViewTimerAdapter(
    private val listener: TimerViewHolderListener
) : ListAdapter<TimerItem, TimerViewHolder>(itemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTimerBinding.inflate(layoutInflater, parent, false)
        return TimerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimerViewHolder, position: Int) {
        holder.bind(listener, getItem(position))
    }

    override fun onBindViewHolder(
        holder: TimerViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNullOrEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            (payloads.first() as? Payload)?.let {
                holder.bind(listener, getItem(position), it)
            }
        }
    }

    private companion object {

        private val itemComparator = object : DiffUtil.ItemCallback<TimerItem>() {

            override fun areItemsTheSame(oldItem: TimerItem, newItem: TimerItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TimerItem, newItem: TimerItem): Boolean {
                return oldItem == newItem
            }

            override fun getChangePayload(oldItem: TimerItem, newItem: TimerItem): Any? {
                return if (oldItem.id == newItem.id) {
                    when {
                        newItem.currentTime <= 0 && oldItem.currentTime != newItem.currentTime ->
                            Payload.END_TIMER
                        newItem.isStarted && !oldItem.isStarted -> Payload.START_TIMER
                        newItem.isStarted && oldItem.isStarted -> Payload.UPDATE_TIMER
                        !newItem.isStarted && oldItem.isStarted -> Payload.STOP_TIMER
                        else -> null
                    }
                } else null
            }
        }
    }
}


