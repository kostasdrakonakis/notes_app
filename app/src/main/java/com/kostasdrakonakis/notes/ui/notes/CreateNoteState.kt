package com.kostasdrakonakis.notes.ui.notes

import com.kostasdrakonakis.notes.model.State
import com.kostasdrakonakis.notes.network.model.Note
import com.kostasdrakonakis.notes.ui.BaseViewState

class CreateNoteState(data: Note?, error: Throwable?, currentState: Int) :
    BaseViewState<Note>(data, error, currentState) {

    companion object {
        val ERROR_STATE = CreateNoteState(null, Throwable(), State.FAILED.value)
        val LOADING_STATE = CreateNoteState(null, Throwable(), State.LOADING.value)
        val SUCCESS_STATE = CreateNoteState(Note(), null, State.SUCCESS.value)
    }
}