package com.kostasdrakonakis.notes.mvp.note

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.github.kostasdrakonakis.androidnavigator.IntentNavigatorBinder
import com.github.kostasdrakonakis.annotation.Intent
import com.github.kostasdrakonakis.annotation.IntentExtra
import com.github.kostasdrakonakis.annotation.IntentProperty
import com.github.kostasdrakonakis.annotation.IntentType
import com.kostasdrakonakis.notes.R

@Intent(value = [IntentExtra(type = IntentType.INT, parameter = "noteId")])
class NoteActivity : AppCompatActivity() {

    @IntentProperty(value = "noteId")
    @JvmField
    var noteId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        IntentNavigatorBinder.bind(this)
        Log.e("Note Activity noteId: ", noteId.toString())
    }
}
