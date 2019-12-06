package com.kostasdrakonakis.notes.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kostasdrakonakis.notes.model.annotation.ViewModelKey
import com.kostasdrakonakis.notes.ui.note.NoteViewModel
import com.kostasdrakonakis.notes.ui.notes.NoteListViewModel
import com.kostasdrakonakis.notes.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ManagersModule::class])
abstract class ViewModelsModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NoteViewModel::class)
    abstract fun bindNoteViewModel(noteViewModel: NoteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NoteListViewModel::class)
    abstract fun bindNoteListViewModel(noteListViewModel: NoteListViewModel): ViewModel
}
