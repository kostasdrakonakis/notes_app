package com.kostasdrakonakis.notes.model

open class BaseViewState<T>(
    var data: T? = null,
    var error: Throwable? = null,
    var currentState: State = State.LOADING
)
