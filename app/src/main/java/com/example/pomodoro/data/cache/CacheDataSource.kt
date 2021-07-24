package com.example.pomodoro.data.cache

open class CacheDataSource<T> : CacheSource<T> {

    @Volatile
    private var data: T? = null

    override fun get() = data

    override fun required() = data!!

    override fun set(data: T?) {
        this.data = data
    }

    override fun isEmpty() = data == null

    override fun clear() {
        data = null
    }
}