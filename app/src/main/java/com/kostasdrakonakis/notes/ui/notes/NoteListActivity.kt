package com.kostasdrakonakis.notes.ui.notes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.github.kostasdrakonakis.androidnavigator.IntentNavigator
import com.kostasdrakonakis.notes.R
import com.kostasdrakonakis.notes.android.activity.BaseActivity
import com.kostasdrakonakis.notes.extensions.asObservable
import com.kostasdrakonakis.notes.extensions.failure
import com.kostasdrakonakis.notes.extensions.observe
import com.kostasdrakonakis.notes.extensions.setSchedulers
import com.kostasdrakonakis.notes.extensions.stringText
import com.kostasdrakonakis.notes.model.note.NoteModel
import kotlinx.android.synthetic.main.activity_notes_list.*
import kotlinx.android.synthetic.main.create_note.view.*

class NoteListActivity : BaseActivity() {

    private val adapter: NotesAdapter by lazy { NotesAdapter() }
    private val noteListViewModel: NoteListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)
        lifecycle.addObserver(noteListViewModel)
        observe(noteListViewModel.notesData, { data ->
            if (data != null) showNotes(data)
        })
        failure(noteListViewModel.errorData, { throwable ->
            if (throwable != null) showError(throwable)
        })
        observe(noteListViewModel.createNote, { success ->
            Toast.makeText(
                this,
                getString(R.string.note_status_message) + success,
                Toast.LENGTH_SHORT
            ).show()
        })
        recyclerView.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        recyclerView.adapter = adapter
        createButton.setOnClickListener {
            showCreateDialog()
        }
        compositeDisposable.add(adapter.noteId.setSchedulers().subscribe { id ->
            IntentNavigator.startNoteActivity(this, id!!)
        })
    }

    private fun showNotes(data: List<NoteModel>) {
        errorView.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        adapter.setItems(data)
    }

    private fun showError(message: String?) {
        errorView.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        logger.error(message)
    }

    @SuppressLint("InflateParams")
    private fun showCreateDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.create_note)
        val view: View = layoutInflater.inflate(R.layout.create_note, null, false)
        builder.setView(view)
        builder.setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
        builder.setPositiveButton(R.string.create) { _, _ ->
            run {
                noteListViewModel.createNote(view.inputText.stringText())
            }
        }
        val dialog = builder.create()
        dialog.show()
        val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        positiveButton.isEnabled = false
        compositeDisposable.add(view.inputText.asObservable()
            .setSchedulers()
            .subscribe { text: String? ->
                positiveButton.isEnabled = !text.isNullOrEmpty()
            })
    }
}
