package com.example.pomodoro

import android.app.Application
import android.content.Intent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.example.pomodoro.data.timer.TimerRepository
import com.example.pomodoro.di.dataModule
import com.example.pomodoro.di.viewModelModule
import com.example.pomodoro.di.viewModule
import com.example.pomodoro.ui.service.ForegroundService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.GlobalContext.startKoin

class App : Application(), KoinComponent {

    val timerRepository: TimerRepository by inject()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)

            modules(dataModule)
            modules(viewModelModule)
            modules(viewModule)
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {
        val startIntent = Intent(this, ForegroundService::class.java)
        startService(startIntent)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForegrounded() {
        val stopIntent = Intent(this, ForegroundService::class.java)
        startService(stopIntent)
    }

}