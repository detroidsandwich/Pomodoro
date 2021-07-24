package com.example.pomodoro.data.common

import android.content.Context
import java.io.IOException
import java.nio.charset.Charset

object AssertsUtils {

    fun getJsonFromAssets(context: Context, fileName: String?): String? {
        return try {
            val input = context.assets.open(fileName!!)
            val size = input.available()
            val buffer = ByteArray(size)
            input.read(buffer)
            input.close()
            String(buffer, Charset.defaultCharset())
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }
}