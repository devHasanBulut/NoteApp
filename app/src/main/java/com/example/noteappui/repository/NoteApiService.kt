package com.example.noteappui.repository

import com.example.noteappui.data.CategoryModel
import com.example.noteappui.data.DateModel
import com.example.noteappui.data.NotesModel
import retrofit2.Call
import retrofit2.http.*

interface NotesApiService {

    @GET("notes")
    fun getAllNotes(): Call<List<NotesModel>>

    @POST("notes")
    fun createNote(@Body note: NotesModel): Call<Void>

    @GET("notes/{id}")
    fun getNoteById(@Path("id") id: Int): Call<NotesModel>

    @PUT("notes/{id}")
    fun updateNote(@Path("id") id: Int, @Body note: NotesModel): Call<Void>

    @DELETE("notes/{id}")
    fun deleteNote(@Path("id") id: Int): Call<Void>

    @GET("notes")
    fun getAllCategories(): Call<List<CategoryModel>>

    @GET ("notes")
    fun getAllDates(): Call<List<DateModel>>
}