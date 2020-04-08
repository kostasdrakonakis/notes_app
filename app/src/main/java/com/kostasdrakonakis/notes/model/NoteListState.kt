package com.kostasdrakonakis.notes.model

import com.kostasdrakonakis.notes.network.model.Note

class NoteListState(data: List<Note>?, error: Throwable?, currentState: State) :
    BaseViewState<List<Note>>(data, error, currentState) {

    companion object {
        val ERROR_STATE = NoteListState(
            null,
            Throwable(),
            State.FAILED
        )
        val LOADING_STATE = NoteListState(
            null,
            Throwable(),
            State.LOADING
        )
        val SUCCESS_STATE = NoteListState(
            arrayListOf(),
            null,
            State.SUCCESS
        )
    }
}