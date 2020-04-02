package com.kostasdrakonakis.notes.ui.note

import com.kostasdrakonakis.notes.model.State
import com.kostasdrakonakis.notes.network.model.Note
import com.kostasdrakonakis.notes.ui.BaseViewState

class NoteState(data: Note?, error: Throwable?, currentState: Int) :
    BaseViewState<Note>(data, error, currentState) {

    companion object {
        val ERROR_STATE = NoteState(null, Throwable(), State.FAILED.value)
        val LOADING_STATE = NoteState(null, Throwable(), State.LOADING.value)
        val SUCCESS_STATE = NoteState(Note(), null, State.SUCCESS.value)
    }
}