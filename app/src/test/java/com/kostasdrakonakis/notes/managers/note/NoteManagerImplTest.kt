package com.kostasdrakonakis.notes.managers.note

import com.google.common.truth.Truth.assertThat
import com.kostasdrakonakis.notes.common.BaseWebServerUnitTest
import okhttp3.mockwebserver.MockResponse
import org.junit.Test
import org.koin.core.qualifier.named
import org.koin.test.inject

class NoteManagerImplTest : BaseWebServerUnitTest() {

    private val noteManager by inject<NoteManager>()
    private val listResponse by inject<MockResponse>(named(LIST_RESPONSE))
    private val deleteListResponse by inject<MockResponse>(named(DELETE_LIST_RESPONSE))
    private val updateResponse by inject<MockResponse>(named(UPDATE_RESPONSE))
    private val noteResponse by inject<MockResponse>(named(NOTE_RESPONSE))

    @Test
    fun createNote() {
        enqueue(noteResponse)
        val note = noteManager.createNote("Hello").blockingGet()
        assertThat(note.title).isEqualTo("Hello")
        assertThat(note.id).isEqualTo(1)
    }

    @Test
    fun getNote() {
        enqueue(noteResponse)
        val note = noteManager.getNote(1).blockingGet()
        assertThat(note.title).isEqualTo("Hello")
        assertThat(note.id).isEqualTo(1)
    }

    @Test
    fun editNote() {
        enqueue(updateResponse)
        val note = noteManager.editNote(2, "World").blockingGet()
        assertThat(note.title).isEqualTo("World")
        assertThat(note.id).isEqualTo(2)
    }

    @Test
    fun deleteNote() {
        enqueue(listResponse)
        noteManager.deleteNote(1).blockingAwait()
        enqueue(deleteListResponse)
        val result = noteManager.getNotes().blockingGet()
        val listRequest = takeRequest()
        val deleteListRequest = takeRequest()
        assertThat(listRequest.sequenceNumber).isEqualTo(0)
        assertThat(deleteListRequest.sequenceNumber).isEqualTo(1)
        assertThat(result.size).isEqualTo(1)
        assertThat(result[0].id).isEqualTo(2)
        assertThat(result[0].title).isEqualTo("World")
    }

    @Test
    fun getNotes() {
        enqueue(listResponse)
        val notes = noteManager.getNotes().blockingGet()
        assertThat(notes.size).isEqualTo(2)
        assertThat(notes[0].title).isEqualTo("Hello")
        assertThat(notes[0].id).isEqualTo(1)
        assertThat(notes[1].title).isEqualTo("World")
        assertThat(notes[1].id).isEqualTo(2)
    }
}