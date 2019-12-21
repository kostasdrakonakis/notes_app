package com.kostasdrakonakis.notes.managers.note

import com.kostasdrakonakis.notes.managers.api.ApiProvider
import com.kostasdrakonakis.notes.network.model.Note
import com.kostasdrakonakis.notes.network.model.NoteBody
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class NoteManagerImpl @Inject constructor(private val apiProvider: ApiProvider) : NoteManager {
    override fun createNote(title: String): Single<Note> {
        return apiProvider.notesApi.createNote(NoteBody(title))
    }

    override fun getNote(id: Int): Single<Note> {
        return apiProvider.notesApi.getNote(id)
    }

    override fun editNote(id: Int, title: String): Single<Note> {
        return apiProvider.notesApi.editNote(id)
    }

    override fun deleteNote(id: Int): Completable {
        return apiProvider.notesApi.deleteNote(id)
    }

    override fun getNotes(): Single<List<Note>> {
        return apiProvider.notesApi.getNotes()
    }
}
