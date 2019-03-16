package com.kostasdrakonakis.notes.mvp

interface IActivityPresenter<V : IActivityView> : IPresenter<V> {

    fun onClose()
}