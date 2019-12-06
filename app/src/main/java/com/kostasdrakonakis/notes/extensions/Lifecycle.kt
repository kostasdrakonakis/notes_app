package com.kostasdrakonakis.notes.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
        liveData.observe(this, Observer(body))

fun <L : LiveData<String>> LifecycleOwner.failure(liveData: L, body: (String?) -> Unit) =
        liveData.observe(this, Observer(body))