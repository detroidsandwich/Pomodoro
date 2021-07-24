package com.example.pomodoro.ui.common

import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.google.android.material.internal.ViewUtils.requestApplyInsetsWhenAttached

@SuppressLint("RestrictedApi")
fun View.doOnApplyWindowInsets(block: (View, WindowInsetsCompat, Rect) -> WindowInsetsCompat) {

    val initialPadding = recordInitialPaddingForView(this)

    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        block(v, insets, initialPadding)
    }
    requestApplyInsetsWhenAttached(this)
}

private fun recordInitialPaddingForView(view: View) =
    Rect(view.paddingLeft, view.paddingTop, view.paddingRight, view.paddingBottom)

fun View.addStatusBarInsets() {
    doOnApplyWindowInsets { view, windowInsetsCompat, rect ->
        val insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars())
        view.updatePadding(
            top = rect.top + insets.top
        )
        windowInsetsCompat
    }
}

fun View.addNavigationBarInsets() {
    doOnApplyWindowInsets { view, windowInsetsCompat, rect ->
        val insets =
            windowInsetsCompat.getInsets(WindowInsetsCompat.Type.ime() or WindowInsetsCompat.Type.statusBars())
        view.updatePadding(
            bottom = rect.bottom + insets.bottom
        )
        windowInsetsCompat
    }
}

