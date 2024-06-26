package com.example.noteappui.domain

import com.example.noteappui.Dependencies
import com.example.noteappui.data.NotesModelDao
import com.example.noteappui.presentation.CategoryViewEntity

class GetCategoryViewEntityUseCase(
    private val notesModelDao: NotesModelDao? = Dependencies.notesModelDao
) {
    fun execute() = notesModelDao?.getAllCategory()?.map {
        CategoryViewEntity(
            category = it.category
        )
    }
}