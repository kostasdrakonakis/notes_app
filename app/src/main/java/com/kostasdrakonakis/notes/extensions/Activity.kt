package com.kostasdrakonakis.notes.extensions

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.setActionbarTitle(@StringRes resId: Int) {
    supportActionBar?.title = getString(resId)
}
