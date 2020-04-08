package com.kostasdrakonakis.notes.model

import com.kostasdrakonakis.notes.network.model.Note

class CreateNoteState(data: Note?, error: Throwable?, currentState: State) :
    BaseViewState<Note>(data, error, currentState) {

    companion object {
        val ERROR_STATE = CreateNoteState(
            null,
            Throwable(),
            State.FAILED
        )
        val LOADING_STATE = CreateNoteState(
            null,
            Throwable(),
            State.LOADING
        )
        val SUCCESS_STATE = CreateNoteState(
            Note(),
            null,
            State.SUCCESS
        )
    }
}