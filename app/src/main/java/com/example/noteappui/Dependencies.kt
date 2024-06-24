package com.example.noteappui

import android.content.Context
import com.example.noteappui.data.AppDatabase
import com.example.noteappui.data.NotesModelDao

object Dependencies {

    var notesModelDao: NotesModelDao? = null
    fun init(context: Context) {
        notesModelDao = AppDatabase.getInstance(context).notesModelDao()
    }
}