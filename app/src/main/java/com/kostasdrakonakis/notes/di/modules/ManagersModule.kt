package com.kostasdrakonakis.notes.di.modules

import com.kostasdrakonakis.notes.MainApplication
import com.kostasdrakonakis.notes.managers.api.ApiProvider
import com.kostasdrakonakis.notes.managers.api.ApiProviderImpl
import com.kostasdrakonakis.notes.managers.note.NoteManager
import com.kostasdrakonakis.notes.managers.note.NoteManagerImpl
import com.kostasdrakonakis.notes.network.api.NotesApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class ManagersModule {
    @Provides
    @Singleton
    fun provideApiProvider(application: MainApplication, notesApi: NotesApi): ApiProvider {
        println("Print PackageName: ${application.packageName}")
        return ApiProviderImpl(notesApi)
    }

    @Provides
    @Singleton
    fun provideNoteManager(apiProvider: ApiProvider): NoteManager {
        return NoteManagerImpl(apiProvider)
    }
}
