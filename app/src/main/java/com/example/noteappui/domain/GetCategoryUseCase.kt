package com.example.noteappui.domain

import com.example.noteappui.presentation.CategoryViewEntity
import com.example.noteappui.repository.NoteRepository

class GetCategoryUseCase(
    private  val noteRepository: NoteRepository
) {
    suspend fun execute() =  noteRepository.getCategory().map {
        CategoryViewEntity(
            category = it.category
        )
    }
}