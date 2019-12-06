package com.kostasdrakonakis.notes.viewmodels

import com.kostasdrakonakis.notes.di.components.DaggerViewModelsComponent
import com.kostasdrakonakis.notes.di.components.ViewModelsComponent
import com.kostasdrakonakis.notes.di.modules.ManagersModule
import com.kostasdrakonakis.notes.di.modules.NetworkModule
import javax.inject.Inject

class ViewModels private constructor() {
    private var component: ViewModelsComponent? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    companion object {
        private lateinit var instance: ViewModels

        val viewModelFactory: ViewModelFactory get() = instance.viewModelFactory

        fun init() {
            instance = ViewModels()
            instance.component = DaggerViewModelsComponent
                .builder()
                .managersModule(ManagersModule())
                .networkModule(NetworkModule())
                .build()

            instance.component?.inject(instance)
        }
    }
}