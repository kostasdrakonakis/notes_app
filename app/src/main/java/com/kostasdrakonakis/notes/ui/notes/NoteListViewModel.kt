package com.kostasdrakonakis.notes.ui.notes

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.kostasdrakonakis.notes.extensions.setSchedulers
import com.kostasdrakonakis.notes.managers.note.NoteManager
import com.kostasdrakonakis.notes.model.CreateNoteState
import com.kostasdrakonakis.notes.model.NoteListState
import com.kostasdrakonakis.notes.network.model.Note
import com.kostasdrakonakis.notes.ui.BaseViewModel

class NoteListViewModel() : BaseViewModel() {

    val noteListState: MutableLiveData<NoteListState> = MutableLiveData()
    val createNoteState: MutableLiveData<CreateNoteState> = MutableLiveData()
    private lateinit var testNoteManager: NoteManager

    @VisibleForTesting
    constructor(testNoteManager: NoteManager) : this() {
        this.testNoteManager = testNoteManager
    }

    @VisibleForTesting
    fun fetchDummyData() {
        testNoteManager.getNotes()
            .doOnSubscribe { onLoading() }
            .subscribe(this::onSuccess, this::onListError)
            .also {
                compositeDisposable.add(it)
            }
    }

    @VisibleForTesting
    fun createDummyNote(title: String) {
        testNoteManager.createNote(title)
            .doOnSubscribe { onNoteLoading() }
            .subscribe(this::onSuccess, this::onError)
            .also {
                compositeDisposable.add(it)
            }
    }

    fun createNote(title: String) {
        noteManager.createNote(title)
            .doOnSubscribe { onNoteLoading() }
            .setSchedulers()
            .subscribe(this::onSuccess, this::onError)
            .also {
                compositeDisposable.add(it)
            }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun fetchRealData() {
        noteManager.getNotes()
            .doOnSubscribe { onLoading() }
            .setSchedulers()
            .subscribe(this::onSuccess, this::onListError)
            .also {
                compositeDisposable.add(it)
            }
    }

    private fun onLoading() {
        noteListState.postValue(NoteListState.LOADING_STATE)
    }

    private fun onNoteLoading() {
        createNoteState.postValue(CreateNoteState.LOADING_STATE)
    }

    private fun onSuccess(notes: List<Note>?) {
        NoteListState.SUCCESS_STATE.data = notes
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
