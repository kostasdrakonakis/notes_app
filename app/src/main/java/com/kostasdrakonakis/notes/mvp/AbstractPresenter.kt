package com.kostasdrakonakis.notes.mvp

import com.kostasdrakonakis.notes.util.LogUtil
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.slf4j.Logger
import java.lang.ref.WeakReference

abstract class AbstractPresenter<V : IView> : IPresenter<V> {
    private var mView: WeakReference<V>? = null
    private lateinit var compositeDisposable: CompositeDisposable
    protected val logger: Logger = LogUtil.getLogger(javaClass)

    override fun attachView(view: V) {
        this.mView = WeakReference(view)
        compositeDisposable = CompositeDisposable()
        onViewAttached(view)
    }

    override fun detachView(view: V) {
        if (view != this.mView?.get()) {
            throw IllegalStateException("Detaching illegal mView")
        }

        this.mView = null
        onViewDetached(view)
        dispose()
    }

    protected abstract fun onViewAttached(view: V)

    protected abstract fun onViewDetached(view: V)

    protected val view: V? get() = mView?.get()

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    protected fun dispose(disposable: Disposable) {
        compositeDisposable.remove(disposable)
    }

    private fun dispose() {
        compositeDisposable.dispose()
    }
}