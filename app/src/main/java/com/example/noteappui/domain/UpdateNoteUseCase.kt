package com.example.noteappui.domain

import com.example.noteappui.data.localdatasource.NotesModel
import com.example.noteappui.presentation.NoteViewEntity
import com.example.noteappui.repository.NoteRepository

class UpdateNoteUseCase(
    private val noteRepository: NoteRepository
) {
    suspend fun execute(noteViewEntity: NoteViewEntity) {
        noteRepository.updateNote(
            NotesModel(
                id = noteViewEntity.id, title = noteViewEntity.title, description = noteViewEntity.description, category = noteViewEntity.category, date = noteViewEntity.date.toLong()
            )
        )
    }

}

