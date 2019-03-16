package com.kostasdrakonakis.notes.managers.note

import com.kostasdrakonakis.notes.managers.api.ApiProvider
import com.kostasdrakonakis.notes.network.model.Note
import com.kostasdrakonakis.notes.network.model.NoteBody
import com.kostasdrakonakis.notes.util.Completables
import com.kostasdrakonakis.notes.util.Singles
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class NoteManagerImpl @Inject constructor(private val apiProvider: ApiProvider) : NoteManager {

    override fun createNote(title: String, callback: NoteManager.Callback<Note>): Disposable {
        return apiProvider.getNotesApi().createNote(NoteBody(title))
            .compose(Singles.setSchedulers())
            .subscribe { note, throwable ->
                if (note != null) callback.onSuccess(note)
                if (throwable != null) callback.onFailure(throwable)
            }
    }

    override fun getNote(id: Int, callback: NoteManager.Callback<Note>): Disposable {
        return apiProvider.getNotesApi().getNote(id)
            .compose(Singles.setSchedulers())
            .subscribe { note, throwable ->
                if (note != null) callback.onSuccess(note)
                if (throwable != null) callback.onFailure(throwable)
            }
    }

    override fun editNote(id: Int, title: String, callback: NoteManager.Callback<Note>): Disposable {
        return apiProvider.getNotesApi().editNote(id)
            .compose(Singles.setSchedulers())
            .subscribe { note, throwable ->
                if (note != null) callback.onSuccess(note)
                if (throwable != null) callback.onFailure(throwable)
            }
    }

    override fun deleteNote(id: Int): Disposable {
        return apiProvider.getNotesApi().deleteNote(id)
            .compose(Completables.setSchedulers())
            .subscribe()
    }

    override fun getNotes(callback: NoteManager.Callback<List<Note>>): Disposable {
        return apiProvider.getNotesApi().getNotes()
            .compose(Singles.setSchedulers())
            .subscribe { t1: List<Note>?, t2: Throwable? ->
                if (t1 != null) callback.onSuccess(t1)
                if (t2 != null) callback.onFailure(t2)
            }
    }

}