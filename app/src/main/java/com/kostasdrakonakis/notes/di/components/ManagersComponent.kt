package com.kostasdrakonakis.notes.di.components

import com.kostasdrakonakis.notes.di.modules.ManagersModule
import com.kostasdrakonakis.notes.di.modules.NetworkModule
import com.kostasdrakonakis.notes.managers.Managers
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ManagersModule::class])
interface ManagersComponent {
    fun inject(managers: Managers)
}
