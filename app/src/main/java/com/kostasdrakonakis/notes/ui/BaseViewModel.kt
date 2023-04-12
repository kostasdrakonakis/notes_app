package com.kostasdrakonakis.notes.ui

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.kostasdrakonakis.notes.managers.note.NoteManager
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseViewModel : ViewModel(), LifecycleObserver, KoinComponent {
    protected val noteManager by inject<NoteManager>()
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
            compositeDisposable.clear()
        }
    }
}