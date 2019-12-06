package com.kostasdrakonakis.notes.extensions

import android.text.TextUtils
import android.widget.EditText

fun EditText.stringText(): String = this.text.toString()

fun EditText.isEmpty(): Boolean = TextUtils.isEmpty(this.stringText())