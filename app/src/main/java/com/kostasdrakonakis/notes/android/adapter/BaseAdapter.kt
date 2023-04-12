package com.kostasdrakonakis.notes.android.adapter

import android.annotation.SuppressLint
import android.os.Handler
import android.view.View
import androidx.concurrent.futures.DirectExecutor
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.Executor
import java.util.concurrent.Executors

abstract class BaseAdapter<T, V : View> : RecyclerView.Adapter<BaseAdapter.BaseViewHolder<V>>() {

    private var items: List<T>? = null

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<T>) {
        this.items = items
        val executor = Executors.newSingleThreadScheduledExecutor()
        executor.execute { notifyDataSetChanged() }
    }

    fun getItem(position: Int): T? {
        return items?.get(position)
    }

    private fun getItemPosition(item: T): Int? {
        return items?.indexOf(item)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun notifyItemChanged(item: T) {
        val position = getItemPosition(item)
        if (position != null) {
            if (position >= 0) {
                val executor = Executors.newSingleThreadScheduledExecutor()
                executor.execute { notifyDataSetChanged() }
            }
        }
    }

    class BaseViewHolder<V : View>(var view: V) : RecyclerView.ViewHolder(view)
}