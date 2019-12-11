package com.kostasdrakonakis.notes.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

fun EditText.stringText(): String = this.text.toString()

fun EditText.asObservable(): Observable<String> {
    val subject: PublishSubject<String> = PublishSubject.create()
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            subject.onNext(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
    return subject
}