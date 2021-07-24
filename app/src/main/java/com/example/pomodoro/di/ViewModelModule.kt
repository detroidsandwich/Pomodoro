package com.example.pomodoro.di

import com.example.pomodoro.ui.view.TimerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { TimerViewModel(get()) }
}
