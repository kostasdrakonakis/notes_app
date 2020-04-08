package com.kostasdrakonakis.notes.ui.note

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.kostasdrakonakis.notes.extensions.setSchedulers
import com.kostasdrakonakis.notes.managers.note.NoteManager
import com.kostasdrakonakis.notes.model.NoteState
import com.kostasdrakonakis.notes.network.model.Note
import com.kostasdrakonakis.notes.ui.BaseViewModel

class NoteViewModel() : BaseViewModel() {
    private val liveDataState: MutableLiveData<NoteState> = MutableLiveData()
    val noteState: LiveData<NoteState> get() = liveDataState
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
        liveDataState.postValue(NoteState.LOADING_STATE)
    }

    private fun onSuccess(note: Note?) {
        NoteState.SUCCESS_STATE.data = note
        liveDataState.postValue(NoteState.SUCCESS_STATE)
    }

    private fun onError(error: Throwable?) {
        NoteState.ERROR_STATE.error = error
        liveDataState.postValue(NoteState.ERROR_STATE)
    }
}
