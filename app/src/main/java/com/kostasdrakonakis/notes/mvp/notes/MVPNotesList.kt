package com.kostasdrakonakis.notes.mvp.notes

import com.kostasdrakonakis.notes.mvp.IActivityPresenter
import com.kostasdrakonakis.notes.mvp.IActivityView

interface MVPNotesList {
    interface View : IActivityView {
        fun showNotes(data: List<PresentationModel>)

        fun openNote(noteId: Int?)

        fun showError(message: String?)

        fun showCreateDialog()
    }

    interface Presenter : IActivityPresenter<MVPNotesList.View> {
        fun onNoteClicked(noteId: Int?)

        fun onCreateButtonClicked()

        fun onCreateSubmit(text: String)
    }

    class PresentationModel {
        var id: Int? = 0
        var title: String? = null
    }
}