package com.kostasdrakonakis.notes.mvp.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kostasdrakonakis.notes.android.adapter.BaseAdapter
import com.kostasdrakonakis.notes.mvp.notes.model.PresentationModel

class NotesAdapter constructor(private val listener: NoteListener) : BaseAdapter<PresentationModel, View>() {

    interface NoteListener {
        fun onNoteClicked(noteId: Int?)
    }

    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): BaseViewHolder<View> {
        val layoutInflater = LayoutInflater.from(container.context)
        return BaseViewHolder(layoutInflater.inflate(android.R.layout.simple_list_item_1, container, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<View>, position: Int) {
        val textView: TextView = holder.itemView.findViewById(android.R.id.text1)
        val model: PresentationModel? = getItem(position)

        textView.text = model?.title
        textView.setOnClickListener { listener.onNoteClicked(model?.id) }
    }
}