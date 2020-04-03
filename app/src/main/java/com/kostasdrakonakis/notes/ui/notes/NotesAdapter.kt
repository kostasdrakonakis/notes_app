package com.kostasdrakonakis.notes.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kostasdrakonakis.notes.android.adapter.BaseAdapter
import com.kostasdrakonakis.notes.network.model.Note
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class NotesAdapter : BaseAdapter<Note, View>() {

    private val noteSubject = PublishSubject.create<Int>()

    val noteId: Observable<Int> = noteSubject

    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): BaseViewHolder<View> {
        val layoutInflater = LayoutInflater.from(container.context)
        return BaseViewHolder(
            layoutInflater.inflate(android.R.layout.simple_list_item_1, container, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<View>, position: Int) {
        val textView: TextView = holder.itemView.findViewById(android.R.id.text1)
        val model: Note? = getItem(position)

        textView.text = model?.title
        val noteItemId: Int = if (model == null) {
            -1
        } else {
            model.id!!
        }
        textView.setOnClickListener { noteSubject.onNext(noteItemId) }
    }
}
