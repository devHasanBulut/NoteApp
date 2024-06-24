package com.example.noteappui.domain

import com.example.noteappui.Dependencies
import com.example.noteappui.data.CategoryModel
import com.example.noteappui.data.DateModel
import com.example.noteappui.data.NotesModel
import com.example.noteappui.data.NotesModelDao
import com.example.noteappui.presentation.NoteViewEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class GetNotesViewEntityUseCase(
    private val notesModelDao: NotesModelDao,

) {
    fun execute() = notesModelDao.getAllNotes().map {
        NoteViewEntity(
            title = it.title,
            description = it.description,
            category = it.category,
            date = it.date.toString()
        )
    }




}

