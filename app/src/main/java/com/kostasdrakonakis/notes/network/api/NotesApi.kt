package com.kostasdrakonakis.notes.network.api

import com.kostasdrakonakis.notes.network.model.Note
import com.kostasdrakonakis.notes.network.model.NoteBody
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface NotesApi {

    @POST("notes")
    @Headers("Content-type: application/json")
    fun createNote(@Body body: NoteBody): Single<Response<Note>>

    @GET("notes")
    @Headers("Content-type: application/json")
    fun getNotes(): Single<Response<List<Note>>>

    @PUT("notes/{id}")
    @Headers("Content-type: application/json")
    fun editNote(@Path("id") id: Int): Single<Response<Note>>

    @DELETE("notes/{id}")
    @Headers("Content-type: application/json")
    fun deleteNote(@Path("id") id: Int): Single<Response<Note>>

    @GET("notes/{id}")
    @Headers("Content-type: application/json")
    fun getNote(@Path("id") id: Int): Single<Response<Note>>
}