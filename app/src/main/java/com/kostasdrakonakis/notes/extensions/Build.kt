package com.kostasdrakonakis.notes.extensions

import com.kostasdrakonakis.notes.BuildConfig

inline fun debug(code: () -> Unit) {
    if (BuildConfig.DEBUG) {
        code()
    }
}