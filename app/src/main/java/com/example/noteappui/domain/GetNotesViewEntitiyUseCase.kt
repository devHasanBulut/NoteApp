package com.example.noteappui.domain

import com.example.noteappui.Dependencies
import com.example.noteappui.data.NotesModelDao
import com.example.noteappui.presentation.NoteViewEntity

class GetNotesViewEntityUseCase(
    private val notesModelDao: NotesModelDao? = Dependencies.notesModelDao,
) {
    fun execute() =
        notesModelDao?.getAllNotes()?.map {
            NoteViewEntity(
                id = it.id,
                title = it.title,
                description = it.description,
                category = it.category,
                date = it.date.toString(),
            )
        }
}
