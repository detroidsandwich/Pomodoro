package com.example.pomodoro.ui.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun LifecycleOwner.viewLifecycleLaunch(block: suspend CoroutineScope.() -> Unit): Job =
    this.lifecycleScope.launch(block = block)
