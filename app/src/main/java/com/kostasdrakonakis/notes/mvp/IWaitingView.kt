package com.kostasdrakonakis.notes.mvp

interface IWaitingView : IView {
    fun showWaiting(show: Boolean)
}