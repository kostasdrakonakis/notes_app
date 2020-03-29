package com.kostasdrakonakis.notes.ui

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.kostasdrakonakis.notes.managers.note.NoteManager
import com.kostasdrakonakis.notes.util.LogUtil
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.slf4j.Logger

abstract class BaseViewModel : ViewModel(), LifecycleObserver, KoinComponent {
    protected val logger: Logger = LogUtil.getLogger<BaseViewModel>()
    protected val noteManager by inject<NoteManager>()
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected fun onActivityStopped() {
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }
}