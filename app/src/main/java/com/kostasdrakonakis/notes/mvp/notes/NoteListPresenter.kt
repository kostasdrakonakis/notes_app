package com.kostasdrakonakis.notes.mvp.notes

import com.kostasdrakonakis.notes.managers.Managers
import com.kostasdrakonakis.notes.managers.note.NoteManager
import com.kostasdrakonakis.notes.mvp.ActivityPresenter
import com.kostasdrakonakis.notes.network.model.Note

class NoteListPresenter : ActivityPresenter<MVPNotesList.View>(),
    MVPNotesList.Presenter, NoteManager.Callback<List<Note>> {

    private val noteManager: NoteManager = Managers.getNoteManager

    override fun onViewAttached(view: MVPNotesList.View) {
        addDisposable(noteManager.getNotes(this))
    }

    override fun onViewDetached(view: MVPNotesList.View) {
    }

    override fun onNoteClicked(nodeId: Int) {
    }

    override fun onSuccess(data: List<Note>) {
        logger.error("Got notes success")
    }

    override fun onFailure(throwable: Throwable) {
    }
}