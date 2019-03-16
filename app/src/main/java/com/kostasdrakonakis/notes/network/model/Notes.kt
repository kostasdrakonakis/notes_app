package com.kostasdrakonakis.notes.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Notes {

    @SerializedName("items")
    @Expose
    val notes: List<Note>? = ArrayList()
}
