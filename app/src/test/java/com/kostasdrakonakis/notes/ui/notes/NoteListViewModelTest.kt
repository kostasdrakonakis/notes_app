package com.kostasdrakonakis.notes.ui.notes

import androidx.lifecycle.Observer
import com.kostasdrakonakis.notes.common.BaseViewModelUnitTest
import com.kostasdrakonakis.notes.model.CreateNoteState
import com.kostasdrakonakis.notes.model.NoteListState
import com.kostasdrakonakis.notes.network.model.Note
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class NoteListViewModelTest: BaseViewModelUnitTest() {
    @Mock
    lateinit var listObserver: Observer<NoteListState>
    @Mock
    lateinit var noteObserver: Observer<CreateNoteState>
    private lateinit var viewModel: NoteListViewModel

    @Before
    override fun setUp() {
        super.setUp()
        viewModel = NoteListViewModel(noteManager)
    }

    @Test
    fun testSuccessFetchNotes() {
        viewModel.noteListState.observeForever(listObserver)
        given(noteManager.getNotes()).willReturn(Single.just(arrayListOf()))
        viewModel.fetchDummyData()
        verify(listObserver).onChanged(NoteListState.LOADING_STATE)
        verify(listObserver).onChanged(NoteListState.SUCCESS_STATE)
    }

    @Test
    fun testSuccessCreateNote() {
        viewModel.createNoteState.observeForever(noteObserver)
        val title = "test"
        given(noteManager.createNote(title)).willReturn(Single.just(Note()))
        viewModel.createDummyNote(title)
        verify(noteObserver).onChanged(CreateNoteState.LOADING_STATE)
        verify(noteObserver).onChanged(CreateNoteState.SUCCESS_STATE)
    }

    @Test
    fun testErrorOnFetchNotes() {
        viewModel.noteListState.observeForever(listObserver)
        given(noteManager.getNotes()).willReturn(Single.error(Throwable("API Error")))
        viewModel.fetchDummyData()
        verify(listObserver).onChanged(NoteListState.LOADING_STATE)
        verify(listObserver).onChanged(NoteListState.ERROR_STATE)
    }

    @Test
    fun testErrorOnCreateNote() {
        viewModel.createNoteState.observeForever(noteObserver)
        val title = "test"
        given(noteManager.createNote(title)).willReturn(Single.error(Throwable("API Error")))
        viewModel.createDummyNote(title)
        verify(noteObserver).onChanged(CreateNoteState.LOADING_STATE)
        verify(noteObserver).onChanged(CreateNoteState.ERROR_STATE)
    }
}
