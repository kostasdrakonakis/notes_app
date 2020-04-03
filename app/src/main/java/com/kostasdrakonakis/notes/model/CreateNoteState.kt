package com.kostasdrakonakis.notes.model

import com.kostasdrakonakis.notes.network.model.Note

class CreateNoteState(data: Note?, error: Throwable?, currentState: Int) :
    BaseViewState<Note>(data, error, currentState) {

    companion object {
        val ERROR_STATE = CreateNoteState(
            null,
            Throwable(),
            State.FAILED.value
        )
        val LOADING_STATE = CreateNoteState(
            null,
            Throwable(),
            State.LOADING.value
        )
        val SUCCESS_STATE = CreateNoteState(
            Note(),
            null,
            State.SUCCESS.value
        )
    }
}