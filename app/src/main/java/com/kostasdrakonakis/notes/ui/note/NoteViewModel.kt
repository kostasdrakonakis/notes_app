package com.kostasdrakonakis.notes.ui.note

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.kostasdrakonakis.notes.extensions.setSchedulers
import com.kostasdrakonakis.notes.managers.note.NoteManager
import com.kostasdrakonakis.notes.viewmodels.BaseViewModel
import javax.inject.Inject

class NoteViewModel @Inject constructor(private val noteManager: NoteManager) : BaseViewModel() {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onActivityStarted() {
        compositeDisposable.add(
            noteManager.getNote(1)
                .setSchedulers()
                .subscribe()
        )
    }
}