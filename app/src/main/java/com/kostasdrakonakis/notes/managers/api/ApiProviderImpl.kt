package com.kostasdrakonakis.notes.managers.api

import com.kostasdrakonakis.notes.network.api.NotesApi

open class ApiProviderImpl constructor(private val mNotesApi: NotesApi) : ApiProvider {
    override val notesApi: NotesApi get() = mNotesApi
}
