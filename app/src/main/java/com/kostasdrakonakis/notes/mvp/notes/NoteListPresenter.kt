package com.kostasdrakonakis.notes.mvp.notes

import android.text.TextUtils
import com.kostasdrakonakis.notes.managers.Managers
import com.kostasdrakonakis.notes.managers.note.NoteManager
import com.kostasdrakonakis.notes.mvp.ActivityPresenter
import com.kostasdrakonakis.notes.network.model.Note

class NoteListPresenter : ActivityPresenter<MVPNotesList.View>(),
    MVPNotesList.Presenter, NoteManager.Callback<List<Note>> {

    private val noteManager: NoteManager = Managers.getNoteManager
    private val presentationModels: MutableList<MVPNotesList.PresentationModel> = ArrayList()

    override fun onViewAttached(view: MVPNotesList.View) {
        view.showWaiting(true)
        addDisposable(noteManager.getNotes(this))
    }

    override fun onViewDetached(view: MVPNotesList.View) {
        view.showWaiting(false)
    }

    override fun onNoteClicked(noteId: Int?) {
        val view = getView()
        view?.openNote(noteId)
    }

    override fun onCreateButtonClicked() {
        val view = getView()
        view?.showCreateDialog()
    }

    override fun onCreateSubmit(text: String) {
        noteManager.createNote(text, createListener)
    }

    override fun onSuccess(data: List<Note>?) {
        val view = getView()
        view?.showWaiting(false)

        presentationModels.clear()

        if (data == null || data.isEmpty()) {
            view?.showNotes(presentationModels)
            return
        }

        for (note in data) {
            val presentationModel: MVPNotesList.PresentationModel = MVPNotesList.PresentationModel()
            presentationModel.id = note.id
            presentationModel.title = note.title
            presentationModels.add(presentationModel)
        }

        view?.showNotes(presentationModels)
    }

    override fun onFailure(throwable: Throwable?) {
        val view = getView()
        view?.showWaiting(false)
        view?.showError(throwable?.message)
    }

    private val createListener = object : NoteManager.Callback<Note> {
        override fun onSuccess(data: Note?) {
            val presentationModel: MVPNotesList.PresentationModel = MVPNotesList.PresentationModel()
            presentationModel.id = data?.id
            presentationModel.title = data?.title

            val view = getView()
            if (!TextUtils.isEmpty(presentationModel.title)) {
                presentationModels.add(presentationModel)
                view?.showNotes(presentationModels)
            }
        }

        override fun onFailure(throwable: Throwable?) {
        }
    }
}