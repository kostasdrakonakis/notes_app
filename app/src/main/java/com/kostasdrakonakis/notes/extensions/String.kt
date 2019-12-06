package com.kostasdrakonakis.notes.extensions

import android.text.TextUtils

fun String.isEmpty(): Boolean = TextUtils.isEmpty(this)

inline fun stringBuilder(block: StringBuilder.() -> Unit): String {
    val sb = StringBuilder()
    sb.block()
    return sb.toString()
}