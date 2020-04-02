package com.kostasdrakonakis.notes.ui.note

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.kostasdrakonakis.notes.extensions.setSchedulers
import com.kostasdrakonakis.notes.network.model.Note
import com.kostasdrakonakis.notes.ui.BaseViewModel

class NoteViewModel : BaseViewModel() {
    val noteState: MutableLiveData<NoteState> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onActivityStarted() {
        compositeDisposable.add(noteManager.getNote(1)
            .doOnEvent { _, _ -> onLoading() }
            .setSchedulers()
            .subscribe(this::onSuccess, this::onError))
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
