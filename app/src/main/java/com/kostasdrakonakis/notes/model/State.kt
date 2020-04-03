package com.kostasdrakonakis.notes.model

enum class State(val value: Int) {
    LOADING(0),
    SUCCESS(1),
    FAILED(-1);
}