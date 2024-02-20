package com.example.noteappui

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface NotesModelDao {

    @Query("SELECT * FROM notes")
     fun getAllNotes(): List<NotesModel>

    @Insert
     fun insertNote(notesModel: NotesModel)

    @Delete
    fun deleteNote(notesModel: NotesModel)

    @Update
    fun updateNote(notesModel: NotesModel)

    @Query("SELECT category FROM notes")
    fun getAllCategory(): List<CategoryModel>

    @Query("SELECT date FROM notes")
    fun getAllDate(): List<DateModel>

}