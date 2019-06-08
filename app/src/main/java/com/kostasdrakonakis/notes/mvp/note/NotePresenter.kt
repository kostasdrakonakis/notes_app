package com.kostasdrakonakis.notes.mvp.note

import com.kostasdrakonakis.notes.managers.Managers
import com.kostasdrakonakis.notes.managers.note.NoteManager
import com.kostasdrakonakis.notes.mvp.ActivityPresenter
import com.kostasdrakonakis.notes.network.model.Note

class NotePresenter : ActivityPresenter<MVPNote.View>(), MVPNote.Presenter, NoteManager.Callback<Note> {

    private var noteId: Int = 0
    private val noteManager: NoteManager = Managers.noteManager

    override fun onViewAttached(view: MVPNote.View) {
        view.showWaiting(true)
        addDisposable(noteManager.getNote(noteId, this))
    }

    override fun onViewDetached(view: MVPNote.View) {
        view.showWaiting(false)
    }

    override fun setData(noteId: Int) {
        this.noteId = noteId
    }

    override fun onDeleteClicked(noteId: Int) {
    }

    override fun onSaveClicked(noteId: Int, text: String) {
    }

    override fun onSuccess(data: Note?) {
        val view = getView()
        view?.showWaiting(false)
        view?.showText(data?.title)
    }

    override fun onFailure(throwable: Throwable?) {
        val view = getView()
        view?.showWaiting(false)
        view?.showError(throwable?.message)
    }
}