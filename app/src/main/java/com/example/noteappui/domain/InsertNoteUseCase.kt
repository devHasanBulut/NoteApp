package com.example.noteappui.domain

import com.example.noteappui.data.localdatasource.NotesModel
import com.example.noteappui.presentation.NoteViewEntity
import com.example.noteappui.repository.NoteRepository

class InsertNoteUseCase(
    private val noteRepository: NoteRepository
) {
    suspend fun execute(title: String, description: String) {
        noteRepository.createNote(NotesModel(title = title, description = description))
    }

}

