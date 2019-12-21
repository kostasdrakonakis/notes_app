package com.kostasdrakonakis.notes.di.components

import com.kostasdrakonakis.notes.MainApplication
import com.kostasdrakonakis.notes.di.modules.ManagersModule
import com.kostasdrakonakis.notes.di.modules.NetworkModule
import com.kostasdrakonakis.notes.di.modules.ViewModelsModule
import com.kostasdrakonakis.notes.viewmodels.ViewModels
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ViewModelsModule::class, ManagersModule::class, NetworkModule::class])
interface ViewModelsComponent {
    fun inject(viewModels: ViewModels)

    @Component.Factory
    interface Factory {
        fun withContext(@BindsInstance instance: MainApplication): ViewModelsComponent
    }
}
