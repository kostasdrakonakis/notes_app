package com.kostasdrakonakis.notes.ui.notes

import com.kostasdrakonakis.notes.model.NoteModel
import com.kostasdrakonakis.notes.model.State
import com.kostasdrakonakis.notes.ui.BaseViewState

class NoteListState(data: List<NoteModel>?, error: Throwable?, currentState: Int) :
    BaseViewState<List<NoteModel>>(data, error, currentState) {

    companion object {
        val ERROR_STATE = NoteListState(null, Throwable(), State.FAILED.value)
        val LOADING_STATE = NoteListState(null, Throwable(), State.LOADING.value)
        val SUCCESS_STATE = NoteListState(arrayListOf(), null, State.SUCCESS.value)
    }
}