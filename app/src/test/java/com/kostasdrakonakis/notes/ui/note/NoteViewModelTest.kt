package com.kostasdrakonakis.notes.ui.note

import androidx.lifecycle.Observer
import com.kostasdrakonakis.notes.common.BaseViewModelUnitTest
import com.kostasdrakonakis.notes.model.NoteState
import com.kostasdrakonakis.notes.network.model.Note
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class NoteViewModelTest : BaseViewModelUnitTest() {
    @Mock
    lateinit var noteObserver: Observer<NoteState>
    private lateinit var viewModel: NoteViewModel

    @Before
    override fun setUp() {
        super.setUp()
        viewModel = NoteViewModel(noteManager)
    }

    @Test
    fun testSuccessFetchNotes() {
        viewModel.noteState.observeForever(noteObserver)
        given(noteManager.getNote(1)).willReturn(Single.just(Note()))
        viewModel.fetchDummyData()
        verify(noteObserver).onChanged(NoteState.LOADING_STATE)
        verify(noteObserver).onChanged(NoteState.SUCCESS_STATE)
    }

    @Test
    fun testErrorOnFetchNotes() {
        viewModel.noteState.observeForever(noteObserver)
        given(noteManager.getNote(1)).willReturn(Single.error(Throwable("API Error")))
        viewModel.fetchDummyData()
        verify(noteObserver).onChanged(NoteState.LOADING_STATE)
        verify(noteObserver).onChanged(NoteState.ERROR_STATE)
    }

}