package com.kostasdrakonakis.notes.managers.api

import com.google.common.truth.Truth.assertThat
import com.kostasdrakonakis.notes.BaseUnitTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ApiProviderImplTest: BaseUnitTest() {

    @Test
    fun getNotesApi() {
        val result = apiProvider.notesApi
        assertThat(result).isEqualTo(notesApi)
    }
}