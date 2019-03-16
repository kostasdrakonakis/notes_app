package com.kostasdrakonakis.notes.mvp.notes

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.LinearLayout.VERTICAL
import android.widget.RelativeLayout
import com.github.kostasdrakonakis.androidnavigator.IntentNavigator
import com.kostasdrakonakis.notes.R
import com.kostasdrakonakis.notes.android.activity.BaseMVPActivity

class NoteListActivity : BaseMVPActivity<MVPNotesList.View, MVPNotesList.Presenter>(), MVPNotesList.View,
    NotesAdapter.NoteListener {

    private lateinit var adapter: NotesAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var errorView: View
    private lateinit var rootContainer: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)
        adapter = NotesAdapter(this)
        recyclerView = findViewById(R.id.notes_recycler)
        errorView = findViewById(R.id.error_text)
        rootContainer = findViewById(R.id.root_container)
        recyclerView.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        recyclerView.adapter = adapter
        findViewById<FloatingActionButton>(R.id.create_note_button).setOnClickListener {
            getPresenter().onCreateButtonClicked()
        }
    }

    override fun createPresenter(): MVPNotesList.Presenter {
        return NoteListPresenter()
    }

    override fun showNotes(data: List<MVPNotesList.PresentationModel>) {
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
        getPresenter().onNoteClicked(noteId)
    }

    override fun showCreateDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.create_note)
        val view: View = layoutInflater.inflate(R.layout.create_note, null)
        val inputText: TextInputEditText = view.findViewById(R.id.note_text)
        builder.setView(view)
        builder.setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
        builder.setPositiveButton(R.string.create) { dialog, _ ->
            run {
                val text: String = inputText.text.toString()
                if (!TextUtils.isEmpty(text)) getPresenter().onCreateSubmit(text)
            }
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
}
