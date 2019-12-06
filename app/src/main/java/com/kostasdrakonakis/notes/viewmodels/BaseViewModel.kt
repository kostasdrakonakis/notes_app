package com.kostasdrakonakis.notes.viewmodels

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.kostasdrakonakis.notes.util.LogUtil
import io.reactivex.disposables.CompositeDisposable
import org.slf4j.Logger

abstract class BaseViewModel : ViewModel(), LifecycleObserver {
    protected val logger: Logger = LogUtil.getLogger(javaClass)
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected fun onActivityStopped() {
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }
}