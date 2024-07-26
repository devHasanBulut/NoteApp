package com.example.noteappui.repository


import java.util.Calendar

class NotesModelForFirebase(
    var id : String? = null,
    var title: String = "",
    var description: String = "",
    var category: String = title,
    val date: Long = Calendar.getInstance().timeInMillis
)