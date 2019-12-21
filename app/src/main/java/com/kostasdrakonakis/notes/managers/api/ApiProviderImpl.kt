package com.kostasdrakonakis.notes.managers.api

import com.kostasdrakonakis.notes.network.api.NotesApi
import javax.inject.Inject

class ApiProviderImpl @Inject constructor(private val mNotesApi: NotesApi) : ApiProvider {
    override val notesApi: NotesApi get() = mNotesApi
}
