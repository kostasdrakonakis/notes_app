package com.kostasdrakonakis.notes.ui.note

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.kostasdrakonakis.notes.extensions.setSchedulers
import com.kostasdrakonakis.notes.managers.note.NoteManager
import com.kostasdrakonakis.notes.model.NoteState
import com.kostasdrakonakis.notes.network.model.Note
import com.kostasdrakonakis.notes.ui.BaseViewModel

class NoteViewModel() : BaseViewModel() {
    val noteState: MutableLiveData<NoteState> = MutableLiveData()
    private lateinit var testNoteManager: NoteManager

    @VisibleForTesting
    constructor(testNoteManager: NoteManager) : this() {
        this.testNoteManager = testNoteManager
    }

    @VisibleForTesting
    fun fetchDummyData() {
        testNoteManager.getNote(1)
            .doOnSubscribe { onLoading() }
            .subscribe(this::onSuccess, this::onError)
            .also {
                compositeDisposable.add(it)
            }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onActivityStarted() {
        noteManager.getNote(1)
            .doOnSubscribe { onLoading() }
            .setSchedulers()
            .subscribe(this::onSuccess, this::onError)
            .also {
                compositeDisposable.add(it)
            }
    }

    private fun onLoading() {
        noteState.postValue(NoteState.LOADING_STATE)
    }

    private fun onSuccess(note: Note?) {
        NoteState.SUCCESS_STATE.data = note
        noteState.postValue(NoteState.SUCCESS_STATE)
    }

    private fun onError(error: Throwable?) {
        NoteState.ERROR_STATE.error = error
        noteState.postValue(NoteState.ERROR_STATE)
    }
}
