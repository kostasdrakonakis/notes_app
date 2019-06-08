package com.kostasdrakonakis.notes.mvp.notes

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.github.kostasdrakonakis.androidnavigator.IntentNavigator
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.kostasdrakonakis.notes.R
import com.kostasdrakonakis.notes.android.activity.BaseMVPActivity
import com.kostasdrakonakis.notes.mvp.notes.model.PresentationModel

class NoteListActivity : BaseMVPActivity<MVPNotesList.View, MVPNotesList.Presenter>(), MVPNotesList.View,
    NotesAdapter.NoteListener {

    private lateinit var adapter: NotesAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var errorView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)
        adapter = NotesAdapter(this)
        recyclerView = findViewById(R.id.notes_recycler)
        errorView = findViewById(R.id.error_text)
        recyclerView.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        recyclerView.adapter = adapter
        findViewById<FloatingActionButton>(R.id.create_note_button).setOnClickListener {
            presenter.onCreateButtonClicked()
        }
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
        val inputText: TextInputEditText = view.findViewById(R.id.note_text)
        builder.setView(view)
        builder.setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
        builder.setPositiveButton(R.string.create) { _, _ ->
            run {
                val text: String = inputText.text.toString()
                if (!TextUtils.isEmpty(text)) presenter.onCreateSubmit(text)
            }
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
}
