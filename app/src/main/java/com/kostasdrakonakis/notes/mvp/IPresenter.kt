package com.kostasdrakonakis.notes.mvp

interface IPresenter<V : IView> {
    fun attachView(view: V)

    fun detachView(view: V)
}