package com.kostasdrakonakis.notes.ui

open class BaseViewState<T>(
    var data: T? = null,
    var error: Throwable? = null,
    var currentState: Int = 0
)
