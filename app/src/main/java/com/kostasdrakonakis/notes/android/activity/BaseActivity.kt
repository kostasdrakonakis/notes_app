package com.kostasdrakonakis.notes.android.activity

import androidx.appcompat.app.AppCompatActivity
import com.kostasdrakonakis.notes.util.LogUtil
import io.reactivex.disposables.CompositeDisposable
import org.slf4j.Logger

abstract class BaseActivity: AppCompatActivity() {
    protected val logger: Logger = LogUtil.logger<BaseActivity>()
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onStop() {
        super.onStop()
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }
}