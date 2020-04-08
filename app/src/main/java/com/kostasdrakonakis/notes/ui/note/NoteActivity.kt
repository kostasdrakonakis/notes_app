package com.kostasdrakonakis.notes.ui.note

import android.os.Bundle
import androidx.activity.viewModels
import com.github.kostasdrakonakis.androidnavigator.IntentNavigatorBinder
import com.github.kostasdrakonakis.annotation.Intent
import com.github.kostasdrakonakis.annotation.IntentExtra
import com.github.kostasdrakonakis.annotation.IntentProperty
import com.github.kostasdrakonakis.annotation.IntentType
import com.kostasdrakonakis.notes.R
import com.kostasdrakonakis.notes.android.activity.BaseActivity
import com.kostasdrakonakis.notes.extensions.observe
import com.kostasdrakonakis.notes.model.State

@Intent(value = [IntentExtra(type = IntentType.INT, parameter = "noteId")])
class NoteActivity : BaseActivity() {

    @IntentProperty(value = "noteId")
    @JvmField
    var noteId: Int = 0

    private val noteViewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        IntentNavigatorBinder.bind(this)
        lifecycle.addObserver(noteViewModel)
        observe(noteViewModel.noteState, { state ->
            when (state.currentState) {
                State.LOADING -> showLoading()
                State.SUCCESS -> showText(state.data?.title)
                State.FAILED -> showError(state.error?.message)
            }
        })
    }

    private fun showLoading() {
    }

    private fun showText(text: String?) {
    }

    private fun showError(message: String?) {
    }
}
