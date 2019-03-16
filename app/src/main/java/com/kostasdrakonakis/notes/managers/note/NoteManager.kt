package com.kostasdrakonakis.notes.managers.note

import com.kostasdrakonakis.notes.network.model.Note
import io.reactivex.disposables.Disposable

interface NoteManager {
    interface Callback<D> {
        fun onSuccess(data: D)

        fun onFailure(throwable: Throwable)
    }

    fun getNotes(callback: Callback<List<Note>>): Disposable

    fun getNote(id: Int): Note?

    fun createNote(title: String): Disposable

    fun editNote(id: Int, title: String, callback: Callback<Note>): Disposable

    fun deleteNote(id: Int): Disposable
}