package com.kostasdrakonakis.notes.mvp.note

import com.kostasdrakonakis.notes.mvp.IActivityPresenter
import com.kostasdrakonakis.notes.mvp.IActivityView

interface MVPNote {
    interface View : IActivityView {
        fun showText(text: String?)

        fun showError(message: String?)
    }

    interface Presenter : IActivityPresenter<MVPNote.View> {
        fun setData(noteId: Int)

        fun onDeleteClicked(noteId: Int)

        fun onSaveClicked(noteId: Int, text: String)
    }
}