package com.kostasdrakonakis.notes.viewmodels

import com.kostasdrakonakis.notes.MainApplication
import com.kostasdrakonakis.notes.di.components.DaggerViewModelsComponent
import javax.inject.Inject

class ViewModels private constructor() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    companion object {
        private lateinit var instance: ViewModels

        val viewModelFactory: ViewModelFactory get() = instance.viewModelFactory

        fun init(app: MainApplication) {
            instance = ViewModels()
            DaggerViewModelsComponent.factory().withContext(app).inject(instance)
        }
    }
}
