package com.example.noteappui.domain

import com.example.noteappui.Dependencies
import com.example.noteappui.data.NotesModelDao
import com.example.noteappui.presentation.NoteViewEntity
import com.example.noteappui.repository.ReadNotesFirebase

class GetNotesViewEntityUseCase(
    private val notesModelDao: NotesModelDao? = Dependencies.notesModelDao,
    private val readNotesFirebase: ReadNotesFirebase = ReadNotesFirebase(),
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
