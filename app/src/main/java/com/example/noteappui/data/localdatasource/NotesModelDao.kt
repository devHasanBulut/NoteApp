package com.example.noteappui.data.localdatasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao

interface NotesModelDao {
    @Query("SELECT * FROM notes")
    fun getAllNotes(): List<NotesModel>

    @Query("SELECT * FROM notes WHERE id = :noteId")
    fun getNoteById(noteId: Int): NotesModel?

    @Insert
    fun insertNote(notesModel: NotesModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllNotes(notesModel: List<NotesModel>)

    @Delete
    fun deleteNote(notesModel: NotesModel)

    @Query("SELECT category FROM notes")
    fun getAllCategory(): List<CategoryModel>

    @Query("SELECT date FROM notes")
    fun getAllDate(): List<DateModel>

    @Update
    fun updateNote(notesModel: NotesModel)

}