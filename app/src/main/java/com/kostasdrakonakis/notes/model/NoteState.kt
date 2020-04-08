package com.kostasdrakonakis.notes.model

import com.kostasdrakonakis.notes.network.model.Note

class NoteState(data: Note?, error: Throwable?, currentState: State) :
    BaseViewState<Note>(data, error, currentState) {

    companion object {
        val ERROR_STATE = NoteState(
            null,
            Throwable(),
            State.FAILED
        )
        val LOADING_STATE = NoteState(
            null,
            Throwable(),
            State.LOADING
        )
        val SUCCESS_STATE = NoteState(
            Note(),
            null,
            State.SUCCESS
        )
    }
}