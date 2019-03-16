package com.kostasdrakonakis.notes.managers.api

import com.kostasdrakonakis.notes.network.api.NotesApi
import javax.inject.Inject

class ApiProviderImpl @Inject constructor(private val notesApi: NotesApi) :
    ApiProvider {
    override fun getNotesApi(): NotesApi {
        return notesApi
    }
}