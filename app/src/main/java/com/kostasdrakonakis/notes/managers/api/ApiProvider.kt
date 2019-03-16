package com.kostasdrakonakis.notes.managers.api

import com.kostasdrakonakis.notes.network.api.NotesApi

interface ApiProvider {

    fun getNotesApi(): NotesApi
}