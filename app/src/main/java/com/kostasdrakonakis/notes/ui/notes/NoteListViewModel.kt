package com.kostasdrakonakis.notes.ui.notes

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.*
import com.kostasdrakonakis.notes.extensions.setSchedulers
import com.kostasdrakonakis.notes.managers.note.NoteManager
import com.kostasdrakonakis.notes.model.CreateNoteState
import com.kostasdrakonakis.notes.model.NoteListState
import com.kostasdrakonakis.notes.network.model.Note
import com.kostasdrakonakis.notes.ui.BaseViewModel

class NoteListViewModel() : BaseViewModel(), DefaultLifecycleObserver {

    private val liveDataNoteListState = MutableLiveData<NoteListState>()
    private var liveDataCreateNoteState = MutableLiveData<CreateNoteState>()
    val noteListState: LiveData<NoteListState> get() = liveDataNoteListState
    val createNoteState: LiveData<CreateNoteState> get() = liveDataCreateNoteState
    private lateinit var testNoteManager: NoteManager

    @VisibleForTesting
    constructor(testNoteManager: NoteManager) : this() {
        this.testNoteManager = testNoteManager
    }

    @VisibleForTesting
    fun fetchDummyData() {
        testNoteManager.getNotes()
            .doOnSubscribe { onLoading() }
            .subscribe(this::onSuccess, this::onListError)
            .also {
                compositeDisposable.add(it)
            }
    }

    @VisibleForTesting
    fun createDummyNote(title: String) {
        testNoteManager.createNote(title)
            .doOnSubscribe { onNoteLoading() }
            .subscribe(this::onSuccess, this::onError)
            .also {
                compositeDisposable.add(it)
            }
    }

    fun createNote(title: String) {
        noteManager.createNote(title)
            .doOnSubscribe { onNoteLoading() }
            .setSchedulers()
            .subscribe(this::onSuccess, this::onError)
            .also {
                compositeDisposable.add(it)
            }
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        noteManager.getNotes()
            .doOnSubscribe { onLoading() }
            .setSchedulers()
            .subscribe(this::onSuccess, this::onListError)
            .also {
                compositeDisposable.add(it)
            }
    }

    private fun onLoading() {
        liveDataNoteListState.postValue(NoteListState.LOADING_STATE)
    }

    private fun onNoteLoading() {
        liveDataCreateNoteState.postValue(CreateNoteState.LOADING_STATE)
    }

    private fun onSuccess(notes: List<Note>?) {
        NoteListState.SUCCESS_STATE.data = notes
        liveDataNoteListState.postValue(NoteListState.SUCCESS_STATE)
    }

    private fun onSuccess(note: Note?) {
        CreateNoteState.SUCCESS_STATE.data = note
        liveDataCreateNoteState.postValue(CreateNoteState.SUCCESS_STATE)
    }

    private fun onError(error: Throwable?) {
        CreateNoteState.ERROR_STATE.error = error
        liveDataCreateNoteState.postValue(CreateNoteState.ERROR_STATE)
    }

    private fun onListError(error: Throwable?) {
        NoteListState.ERROR_STATE.error = error
        liveDataNoteListState.postValue(NoteListState.ERROR_STATE)
    }
}
