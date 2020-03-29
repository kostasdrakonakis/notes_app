package com.kostasdrakonakis.notes.ui.notes

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.kostasdrakonakis.notes.extensions.setSchedulers
import com.kostasdrakonakis.notes.model.NoteModel
import com.kostasdrakonakis.notes.network.model.Note
import com.kostasdrakonakis.notes.ui.BaseViewModel

class NoteListViewModel : BaseViewModel() {

    val notesData: MutableLiveData<List<NoteModel>> = MutableLiveData()
    val createNote: MutableLiveData<Boolean> = MutableLiveData()
    val errorData: MutableLiveData<String> = MutableLiveData()

    fun createNote(title: String) {
        compositeDisposable.add(
            noteManager.createNote(title)
                .setSchedulers()
                .subscribe { data: Note?, throwable: Throwable? ->
                    createNote.postValue(data != null)
                    errorData.postValue(throwable?.message)
                })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onActivityStarted() {
        compositeDisposable.add(
            noteManager.getNotes()
                .setSchedulers()
                .subscribe { notes: List<Note>?, throwable: Throwable? ->
                    if (notes == null) {
                        notesData.postValue(null)
                        errorData.postValue(throwable?.message)
                    } else {
                        val noteModelList = arrayListOf<NoteModel>()
                        for (note: Note in notes) {
                            noteModelList.add(
                                NoteModel(
                                    note.id,
                                    note.title
                                )
                            )
                        }
                        notesData.postValue(noteModelList)
                        errorData.postValue(null)
                    }
                }
        )
    }
}
