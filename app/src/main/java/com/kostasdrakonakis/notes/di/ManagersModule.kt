package com.kostasdrakonakis.notes.di

import com.kostasdrakonakis.notes.managers.api.ApiProvider
import com.kostasdrakonakis.notes.managers.api.ApiProviderImpl
import com.kostasdrakonakis.notes.managers.note.NoteManager
import com.kostasdrakonakis.notes.managers.note.NoteManagerImpl
import org.koin.dsl.module

val managersModule = module {
    single<ApiProvider> {
        return@single ApiProviderImpl(get())
    }

    single<NoteManager> {
        return@single NoteManagerImpl(get())
    }
}