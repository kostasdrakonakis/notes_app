package com.kostasdrakonakis.notes.di.components

import com.kostasdrakonakis.notes.di.modules.ManagersModule
import com.kostasdrakonakis.notes.di.modules.NetworkModule
import com.kostasdrakonakis.notes.di.modules.ViewModelsModule
import com.kostasdrakonakis.notes.viewmodels.ViewModels
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelsModule::class, ManagersModule::class, NetworkModule::class])
interface ViewModelsComponent {
    fun inject(viewModels: ViewModels)
}
