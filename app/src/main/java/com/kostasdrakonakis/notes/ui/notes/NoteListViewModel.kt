package com.kostasdrakonakis.notes.ui.notes

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.kostasdrakonakis.notes.extensions.setSchedulers
import com.kostasdrakonakis.notes.model.NoteModel
import com.kostasdrakonakis.notes.network.model.Note
import com.kostasdrakonakis.notes.ui.BaseViewModel

class NoteListViewModel : BaseViewModel() {

    val noteListState: MutableLiveData<NoteListState> = MutableLiveData()
    val createNoteState: MutableLiveData<CreateNoteState> = MutableLiveData()

    fun createNote(title: String) {
        compositeDisposable.add(
            noteManager.createNote(title)
                .doOnEvent { _, _ -> onNoteLoading() }
                .setSchedulers()
                .subscribe(this::onSuccess, this::onError))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onActivityStarted() {
        compositeDisposable.add(noteManager.getNotes()
            .doOnEvent { _, _ -> onLoading() }
            .setSchedulers()
            .subscribe(this::onSuccess, this::onListError)
        )
    }

    private fun onLoading() {
        noteListState.postValue(NoteListState.LOADING_STATE)
    }

    private fun onNoteLoading() {
        createNoteState.postValue(CreateNoteState.LOADING_STATE)
    }

    private fun onSuccess(notes: List<Note>?) {
        val data = notes?.map { NoteModel(it.id, it.title) }
        NoteListState.SUCCESS_STATE.data = data
        noteListState.postValue(NoteListState.SUCCESS_STATE)
    }

    private fun onSuccess(note: Note?) {
        CreateNoteState.SUCCESS_STATE.data = note
        createNoteState.postValue(CreateNoteState.SUCCESS_STATE)
    }

    private fun onError(error: Throwable?) {
        CreateNoteState.ERROR_STATE.error = error
        createNoteState.postValue(CreateNoteState.ERROR_STATE)
    }

    private fun onListError(error: Throwable?) {
        NoteListState.ERROR_STATE.error = error
        noteListState.postValue(NoteListState.ERROR_STATE)
    }
}
