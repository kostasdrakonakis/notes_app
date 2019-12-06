package com.kostasdrakonakis.notes.managers.note

import com.kostasdrakonakis.notes.network.model.Note
import io.reactivex.Completable
import io.reactivex.Single

interface NoteManager {
    fun getNotes(): Single<List<Note>>

    fun getNote(id: Int): Single<Note>

    fun createNote(title: String): Single<Note>

    fun editNote(id: Int, title: String): Single<Note>

    fun deleteNote(id: Int): Completable
}