package com.example.noteappui.domain

import com.example.noteappui.data.localdatasource.NotesModelDao
import com.example.noteappui.presentation.DateViewEntity
import com.example.noteappui.presentation.NoteViewEntity
import com.example.noteappui.repository.NoteRepository
import java.util.Calendar
import java.util.Locale

class GetDateViewEntityUseCase(
    private val noteRepository: NoteRepository
) {
    fun execute(noteViewEntities: List<NoteViewEntity>) = noteViewEntities.map {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = it.date.toLong()
        val dayName =
            calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault())
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault())
        DateViewEntity(
            dayName = dayName, day = day, month = month
        )
    }
}