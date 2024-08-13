package com.example.noteappui.domain

import com.example.noteappui.Dependencies.notesModelDao
import com.example.noteappui.data.NotesModel
import com.example.noteappui.data.RetrofitClient

class InsertNote {


     fun execute(newNote: NotesModel){
        notesModelDao?.insertNote(newNote)
    }

    fun addNewNoteDb(){
        val response = RetrofitClient.api.getAllNotes().execute()
        if (response.isSuccessful){
            val notes = response.body()
            notes?.forEach {
                notesModelDao?.insertNote(it)
            }
        }
    }

     fun testAddNewNote(title: String, description: String, category: String, date: Long){
        val newNote = NotesModel(
            title = title,
            description = description,
            category = category,
            date = date
        )
        execute(newNote)

    }

}

