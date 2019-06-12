package com.kostasdrakonakis.notes.mvp.notes

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.github.kostasdrakonakis.androidnavigator.IntentNavigator
import com.kostasdrakonakis.notes.R
import com.kostasdrakonakis.notes.android.activity.BaseMVPActivity
import com.kostasdrakonakis.notes.mvp.notes.model.PresentationModel
import kotlinx.android.synthetic.main.activity_notes_list.*
import kotlinx.android.synthetic.main.create_note.view.*

class NoteListActivity : BaseMVPActivity<MVPNotesList.View, MVPNotesList.Presenter>(), MVPNotesList.View,
    NotesAdapter.NoteListener {

    private val adapter: NotesAdapter by lazy { NotesAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)
        recyclerView.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        recyclerView.adapter = adapter
        createButton.setOnClickListener { presenter.onCreateButtonClicked() }
    }

    override fun createPresenter(): MVPNotesList.Presenter {
        return NoteListPresenter()
    }

    override fun showNotes(data: List<PresentationModel>) {
        errorView.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        adapter.setItems(data)
    }

    override fun openNote(noteId: Int?) {
        IntentNavigator.startNoteActivity(this, noteId!!)
    }

    override fun showError(message: String?) {
        errorView.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        logger.error(message)
    }

    override fun onNoteClicked(noteId: Int?) {
        presenter.onNoteClicked(noteId)
    }

    override fun showCreateDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.create_note)
        val view: View = layoutInflater.inflate(R.layout.create_note, null, false)
        builder.setView(view)
        builder.setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
        builder.setPositiveButton(R.string.create) { _, _ ->
            run {
                val text: String = view.inputText.text.toString()
                if (!isEmpty(text)) presenter.onCreateSubmit(text)
            }
        }
        builder.create().show()
    }
}
