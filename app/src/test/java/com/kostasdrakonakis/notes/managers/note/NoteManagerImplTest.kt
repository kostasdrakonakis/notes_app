package com.kostasdrakonakis.notes.managers.note

import com.google.common.truth.Truth.assertThat
import com.kostasdrakonakis.notes.BaseUnitTest
import com.kostasdrakonakis.notes.FileUtils
import okhttp3.mockwebserver.MockResponse
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class NoteManagerImplTest: BaseUnitTest() {

    private lateinit var noteManager: NoteManagerImpl

    override fun setUp() {
        super.setUp()
        noteManager = NoteManagerImpl(apiProvider)
    }

    @Test
    fun createNote() {
        mockWebServer.enqueue(getResponse())
        val note = noteManager.createNote("Hello").blockingGet()
        assertThat(note.title).isEqualTo("Hello")
        assertThat(note.id).isEqualTo(1)
    }

    @Test
    fun getNote() {
        mockWebServer.enqueue(getResponse())
        val note = noteManager.getNote(1).blockingGet()
        assertThat(note.title).isEqualTo("Hello")
        assertThat(note.id).isEqualTo(1)
    }

    @Test
    fun editNote() {
        mockWebServer.enqueue(getUpdatedResponse())
        val note = noteManager.editNote(2, "World").blockingGet()
        assertThat(note.title).isEqualTo("World")
        assertThat(note.id).isEqualTo(2)
    }

    @Test
    fun deleteNote() {
        mockWebServer.enqueue(getListResponse())
        val result = noteManager.deleteNote(1).blockingAwait()
        assertThat(result).isInstanceOf(Unit::class.java)
    }

    @Test
    fun getNotes() {
        mockWebServer.enqueue(getListResponse())
        val notes = noteManager.getNotes().blockingGet()
        assertThat(notes.size).isEqualTo(2)
        assertThat(notes[0].title).isEqualTo("Hello")
        assertThat(notes[0].id).isEqualTo(1)
        assertThat(notes[1].title).isEqualTo("World")
        assertThat(notes[1].id).isEqualTo(2)
    }

    private fun getResponse(): MockResponse {
        return MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(FileUtils.readTestResourceFile("note.json"))
    }

    private fun getUpdatedResponse(): MockResponse {
        return MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(FileUtils.readTestResourceFile("update.json"))
    }

    private fun getListResponse(): MockResponse {
        return MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(FileUtils.readTestResourceFile("list.json"))
    }
}