package com.example.noteappui.domain

import com.example.noteappui.data.NotesModel

class NewNoteForMySQL(
    var title: String,
    var description: String,
    var category: String,
    var date: Long,
){
    val newNote = NotesModel(
        title = title,
        description = description,
        category = category,
        date = date,
    )


}
