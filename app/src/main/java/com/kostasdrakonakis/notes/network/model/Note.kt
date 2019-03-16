package com.kostasdrakonakis.notes.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Note {

    @SerializedName("id")
    @Expose
    val id: Int? = 0

    @SerializedName("title")
    @Expose
    val title: String? = null
}