package com.example.pomodoro.data.common

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJson(json: String?): T? =
    this.fromJson<T>(json, object : TypeToken<T>() {}.type)

inline fun <reified T> Gson.fromMap(map: Map<String, String>): T =
    this.fromJson<T>(toJsonTree(map), object : TypeToken<T>() {}.type)