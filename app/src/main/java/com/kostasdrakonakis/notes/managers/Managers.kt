package com.kostasdrakonakis.notes.managers

import android.app.Application
import com.kostasdrakonakis.notes.di.components.DaggerManagersComponent
import com.kostasdrakonakis.notes.di.components.ManagersComponent
import com.kostasdrakonakis.notes.di.modules.ManagersModule
import com.kostasdrakonakis.notes.di.modules.NetworkModule
import com.kostasdrakonakis.notes.managers.note.NoteManager
import com.kostasdrakonakis.notes.util.LogUtil
import javax.inject.Inject

class Managers private constructor() {
    @Inject
    lateinit var noteManager: NoteManager

    private var component: ManagersComponent? = null

    companion object {
        lateinit var instance: Managers

        val getNoteManager: NoteManager get() = instance.noteManager

        fun initData(app: Application) {
            instance = Managers()
            instance.component = DaggerManagersComponent
                .builder()
                .managersModule(ManagersModule())
                .networkModule(NetworkModule())
                .build()

            instance.component?.inject(instance)
            LogUtil.logInitialData(app)
        }
    }
}
