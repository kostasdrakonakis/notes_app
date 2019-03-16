package com.kostasdrakonakis.notes.mvp.notes

import android.os.Bundle
import android.widget.TextView
import com.github.kostasdrakonakis.androidnavigator.IntentNavigator
import com.kostasdrakonakis.notes.R
import com.kostasdrakonakis.notes.android.activity.BaseMVPActivity

class NoteListActivity : BaseMVPActivity<MVPNotesList.View, MVPNotesList.Presenter>(), MVPNotesList.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)
        findViewById<TextView>(R.id.hello).setOnClickListener {
            IntentNavigator.startNoteActivity(this, 5)
        }
    }

    override fun showNotes() {

    }

    override fun createPresenter(): MVPNotesList.Presenter {
        return NoteListPresenter()
    }
}
