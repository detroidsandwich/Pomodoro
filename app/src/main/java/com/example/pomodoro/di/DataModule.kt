package com.example.pomodoro.di

import com.example.pomodoro.data.timer.TimerDataRepository
import com.example.pomodoro.data.timer.TimerRepository
import com.example.pomodoro.data.timer.cache.TimerCacheDataStore
import com.google.gson.GsonBuilder
import org.koin.dsl.module

val dataModule = module {
    //GSON
    single { GsonBuilder().setLenient().create() }

    //Timer
    single { TimerCacheDataStore() }
    single<TimerRepository> {
        TimerDataRepository(
            get(TimerCacheDataStore::class)
        )
    }
}
