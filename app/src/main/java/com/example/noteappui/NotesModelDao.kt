package com.example.noteappui

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface NotesModelDao {
    @Query("SELECT * FROM notes")
     fun getAllNotes(): List<NotesModel>

    @Insert
     fun insertNote(notesModel: NotesModel)

    @Delete
    fun deleteNote(notesModel: NotesModel)
}