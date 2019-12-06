package com.kostasdrakonakis.notes.android.adapter

import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, V : View> : RecyclerView.Adapter<BaseAdapter.BaseViewHolder<V>>() {

    private var items: List<T>? = null

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    fun setItems(items: List<T>) {
        this.items = items
        Handler().post { notifyDataSetChanged() }
    }

    fun getItem(position: Int): T? {
        return items?.get(position)
    }

    private fun getItemPosition(item: T): Int? {
        return items?.indexOf(item)
    }

    fun notifyItemChanged(item: T) {
        val position = getItemPosition(item)
        if (position != null) {
            if (position >= 0) {
                // Notify item changed in the next cycle of the main thread
                Handler().post { notifyItemChanged(position) }
            }
        }
    }

    class BaseViewHolder<V : View>(var view: V) : RecyclerView.ViewHolder(view)
}