package com.example.noteappui.data.remotedatasource

import com.example.noteappui.data.localdatasource.CategoryModel
import com.example.noteappui.data.localdatasource.NotesModel
import com.example.noteappui.data.localdatasource.DateModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface NotesApiService {

    @GET("notes")
    suspend fun getAllNotes(): List<NotesModel>

    @POST("notes")
    suspend fun createNote(@Body note: NotesModel)

    @GET("notes/{id}")
    suspend fun getNoteById(@Path("id") id: Int): NotesModel

    @PUT("notes/{id}")
    suspend fun updateNote(@Path("id") id: Int, @Body note: NotesModel)

    @DELETE("notes/{id}")
    suspend fun deleteNote(@Path("id") id: Int)

    @DELETE("notes")
    suspend fun deleteAllNotes()

    @GET("notes")
    suspend fun getAllCategories(): List<CategoryModel>

    @GET("notes")
    suspend fun getAllDates(): List<DateModel>

}