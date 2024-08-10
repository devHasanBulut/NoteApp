package com.example.noteappui.domain

import com.example.noteappui.data.NotesModel

class UpdateNoteFromMySQL(
     noteId: Int,
    var title: String,
    var description: String,
    var category: String,
    var date: Long
) {
    val updateNote = NotesModel(
        id = noteId,
        title = title,
        description = description,
        category = category,
        date = date
    )
}