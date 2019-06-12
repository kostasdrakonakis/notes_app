package com.kostasdrakonakis.notes.mvp.notes

import com.kostasdrakonakis.notes.mvp.IActivityPresenter
import com.kostasdrakonakis.notes.mvp.IActivityView
import com.kostasdrakonakis.notes.mvp.notes.model.PresentationModel

interface MVPNotesList {
    interface View : IActivityView {
        fun showNotes(data: List<PresentationModel>)

        fun openNote(noteId: Int?)

        fun showError(message: String?)

        fun showCreateDialog()
    }

    interface Presenter : IActivityPresenter<View> {
        fun onNoteClicked(noteId: Int?)

        fun onCreateButtonClicked()

        fun onCreateSubmit(text: String)
    }
}