package com.example.pomodoro.ui.common

import android.content.res.Resources
import android.util.TypedValue

fun Int.spToPx() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_SP, this.toFloat(), Resources.getSystem().displayMetrics
).toInt()

fun Float.spToPx() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_SP, this, Resources.getSystem().displayMetrics
).toInt()

fun Int.dpToPx() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics
).toInt()

fun Float.dpToPx() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics
)