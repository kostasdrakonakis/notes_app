package com.kostasdrakonakis.notes.mvp.notes

import com.kostasdrakonakis.notes.mvp.IActivityPresenter
import com.kostasdrakonakis.notes.mvp.IActivityView

interface MVPNotesList {
    interface View : IActivityView {
        fun showNotes()
    }

    interface Presenter : IActivityPresenter<MVPNotesList.View> {
        fun onNoteClicked(nodeId: Int)
    }
}