package com.kostasdrakonakis.notes.managers.note

import android.util.Log
import com.kostasdrakonakis.notes.managers.api.ApiProvider
import com.kostasdrakonakis.notes.network.model.Note
import com.kostasdrakonakis.notes.network.model.NoteBody
import com.kostasdrakonakis.notes.util.AppTransformers
import com.kostasdrakonakis.notes.util.Singles
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class NoteManagerImpl @Inject constructor(private val apiProvider: ApiProvider) : NoteManager {

    override fun createNote(title: String): Disposable {
        return apiProvider.getNotesApi().createNote(NoteBody(title))
            .compose(Singles.setSchedulers())
            .compose(AppTransformers.unWrapResponseWithErrorOnStream())
            .subscribe { note, throwable ->
                Log.e("Note", note.title)
            }
    }

    override fun getNote(id: Int): Note? {
        return null
    }

    override fun editNote(id: Int, title: String, callback: NoteManager.Callback<Note>): Disposable {
        return apiProvider.getNotesApi().editNote(id)
            .compose(Singles.setSchedulers())
            .compose(AppTransformers.unWrapResponseWithErrorOnStream())
            .subscribe { note ->
                callback.onSuccess(note)
            }
    }

    override fun deleteNote(id: Int): Disposable {
        return apiProvider.getNotesApi().deleteNote(id)
            .compose(Singles.setSchedulers())
            .compose(AppTransformers.unWrapResponseWithErrorOnStream())
            .subscribe()
    }

    override fun getNotes(callback: NoteManager.Callback<List<Note>>): Disposable {
        return apiProvider.getNotesApi().getNotes()
            .compose(Singles.setSchedulers())
            .compose(AppTransformers.unWrapResponseWithErrorOnStream())
            .subscribe { t1: List<Note>, t2: Throwable ->
                callback.onSuccess(t1)
                callback.onFailure(t2)
            }
    }

}