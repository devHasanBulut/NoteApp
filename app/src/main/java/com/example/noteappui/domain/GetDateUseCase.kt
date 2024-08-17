package com.example.noteappui.domain

import com.example.noteappui.presentation.DateViewEntity
import com.example.noteappui.repository.NoteRepository
import java.util.Calendar
import java.util.Locale

class GetDateUseCase(
    private val noteRepository: NoteRepository
) {
    suspend fun execute() = noteRepository.getDate().map {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = it.date
        val dayName =
            calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault())
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault())
        DateViewEntity(
            dayName = dayName, day = day, month = month
        )
    }
}