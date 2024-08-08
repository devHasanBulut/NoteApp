package com.example.noteappui.domain

import com.example.noteappui.Dependencies.notesModelDao
import com.example.noteappui.data.NotesModel

class InsertNote {
     fun execute(newNote: NotesModel){
        notesModelDao?.insertNote(newNote)

    }

     fun addNewNote(title: String, description: String, category: String, date: Long){
        val newNote = NotesModel(
            title = title,
            description = description,
            category = category,
            date = date
        )
        execute(newNote)

    }
}

