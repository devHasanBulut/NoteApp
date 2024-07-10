package com.example.noteappui.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar


@Entity(tableName = "notes")
data class NotesModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String,
    var description: String,
    var category: String = title,
    val date: Long = Calendar.getInstance().timeInMillis
)
