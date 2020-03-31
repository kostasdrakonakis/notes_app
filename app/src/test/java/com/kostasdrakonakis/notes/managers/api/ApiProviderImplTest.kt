package com.kostasdrakonakis.notes.managers.api

import com.google.common.truth.Truth.assertThat
import com.kostasdrakonakis.notes.BaseUnitTest
import com.kostasdrakonakis.notes.network.api.NotesApi
import org.junit.Test
import org.koin.test.inject

class ApiProviderImplTest : BaseUnitTest() {

    private val apiProvider by inject<ApiProvider>()
    private val notesApi by inject<NotesApi>()

    @Test
    fun getNotesApi() {
        val result = apiProvider.notesApi
        assertThat(result).isEqualTo(notesApi)
    }
}
