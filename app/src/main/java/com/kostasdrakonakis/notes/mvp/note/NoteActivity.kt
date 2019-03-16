package com.kostasdrakonakis.notes.mvp.note

import android.os.Bundle
import com.github.kostasdrakonakis.androidnavigator.IntentNavigatorBinder
import com.github.kostasdrakonakis.annotation.Intent
import com.github.kostasdrakonakis.annotation.IntentExtra
import com.github.kostasdrakonakis.annotation.IntentProperty
import com.github.kostasdrakonakis.annotation.IntentType
import com.kostasdrakonakis.notes.R
import com.kostasdrakonakis.notes.android.activity.BaseMVPActivity

@Intent(value = [IntentExtra(type = IntentType.INT, parameter = "noteId")])
class NoteActivity : BaseMVPActivity<MVPNote.View, MVPNote.Presenter>(), MVPNote.View {

    @IntentProperty(value = "noteId")
    @JvmField
    var noteId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        IntentNavigatorBinder.bind(this)
        getPresenter().setData(noteId)
        logger.error("Note Activity noteId: " + noteId.toString())
    }

    override fun createPresenter(): MVPNote.Presenter {
        return NotePresenter()
    }

    override fun showText(text: String?) {
    }

    override fun showError(message: String?) {
    }
}
