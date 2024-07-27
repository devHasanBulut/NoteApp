package com.example.noteappui.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.noteappui.presentation.NoteViewEntity


@Dao
interface NotesModelDao {
    @Query("SELECT * FROM notes")
    fun getAllNotes(): List<NotesModel>

    @Query("SELECT * FROM notes WHERE id = :noteId")
    fun getNoteById(noteId: Int): NotesModel?

    @Insert
    fun insertNote(notesModel: NotesModel)

    @Delete
    fun deleteNote(notesModel: NotesModel)

    @Update
    fun updateNoteCategory(notesModel: NotesModel)

    @Update
    fun updateNoteTitle(notesModel: NotesModel)

    @Update
    fun updateNoteDescription(notesModel: NotesModel)

    @Query("SELECT category FROM notes")
    fun getAllCategory(): List<CategoryModel>

    @Query("SELECT date FROM notes")
    fun getAllDate(): List<DateModel>

}