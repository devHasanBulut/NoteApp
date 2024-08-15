package com.example.noteappui.domain

import com.example.noteappui.presentation.NoteViewEntity
import com.example.noteappui.repository.NoteRepository

class GetNotesViewEntityUseCase(
    private val noteRepository: NoteRepository
) {
    suspend fun execute() = noteRepository.getNotes().map {
        NoteViewEntity(
            id = it.id,
            title = it.title,
            description = it.description,
            category = it.category,
            date = it.date.toString(),
        )
    }
}
