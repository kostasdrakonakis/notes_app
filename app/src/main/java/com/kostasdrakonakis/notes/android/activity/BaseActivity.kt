package com.kostasdrakonakis.notes.android.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kostasdrakonakis.notes.util.LogUtil
import com.kostasdrakonakis.notes.viewmodels.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import org.slf4j.Logger

abstract class BaseActivity: AppCompatActivity() {
    protected val logger: Logger = LogUtil.getLogger(javaClass)
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(getViewModel())
    }

    override fun onStop() {
        super.onStop()
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }

    protected abstract fun getViewModel(): BaseViewModel
}