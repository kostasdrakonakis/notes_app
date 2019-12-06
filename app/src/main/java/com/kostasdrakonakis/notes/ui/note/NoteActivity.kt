package com.kostasdrakonakis.notes.ui.note

import android.os.Bundle
import com.github.kostasdrakonakis.androidnavigator.IntentNavigatorBinder
import com.github.kostasdrakonakis.annotation.Intent
import com.github.kostasdrakonakis.annotation.IntentExtra
import com.github.kostasdrakonakis.annotation.IntentProperty
import com.github.kostasdrakonakis.annotation.IntentType
import com.kostasdrakonakis.notes.R
import com.kostasdrakonakis.notes.android.activity.BaseActivity
import com.kostasdrakonakis.notes.extensions.viewModel
import com.kostasdrakonakis.notes.viewmodels.BaseViewModel

@Intent(value = [IntentExtra(type = IntentType.INT, parameter = "noteId")])
class NoteActivity : BaseActivity() {

    @IntentProperty(value = "noteId")
    @JvmField
    var noteId: Int = 0

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        IntentNavigatorBinder.bind(this)
        noteViewModel = viewModel()
    }

    override fun getViewModel(): BaseViewModel {
        return noteViewModel
    }

    fun showText(text: String?) {
    }

    fun showError(message: String?) {
    }
}
