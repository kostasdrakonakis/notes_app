package com.kostasdrakonakis.notes.model

import com.kostasdrakonakis.notes.network.model.Note

class NoteState(data: Note?, error: Throwable?, currentState: Int) :
    BaseViewState<Note>(data, error, currentState) {

    companion object {
        val ERROR_STATE = NoteState(
            null,
            Throwable(),
            State.FAILED.value
        )
        val LOADING_STATE = NoteState(
            null,
            Throwable(),
            State.LOADING.value
        )
        val SUCCESS_STATE = NoteState(
            Note(),
            null,
            State.SUCCESS.value
        )
    }
}