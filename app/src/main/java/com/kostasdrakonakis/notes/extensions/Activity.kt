package com.kostasdrakonakis.notes.extensions

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.kostasdrakonakis.notes.viewmodels.ViewModels

inline fun <reified T : ViewModel> AppCompatActivity.viewModel(): T {
    return ViewModelProviders.of(this, ViewModels.viewModelFactory)[T::class.java]
}

fun AppCompatActivity.setActionbarTitle(@StringRes resId: Int) {
    supportActionBar?.title = getString(resId)
}
