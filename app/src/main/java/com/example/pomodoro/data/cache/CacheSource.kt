package com.example.pomodoro.data.cache

interface CacheSource<T>: Cleanable {

    fun get(): T?

    fun required(): T

    fun set(data: T?)

    fun isEmpty(): Boolean
}