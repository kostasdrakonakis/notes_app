package com.kostasdrakonakis.notes.ui.note

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.kostasdrakonakis.notes.extensions.setSchedulers
import com.kostasdrakonakis.notes.ui.BaseViewModel

class NoteViewModel : BaseViewModel() {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onActivityStarted() {
        compositeDisposable.add(noteManager.getNote(1).setSchedulers().subscribe())
    }
}
