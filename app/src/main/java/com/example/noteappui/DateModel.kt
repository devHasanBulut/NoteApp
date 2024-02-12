package com.example.noteappui

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "date")
data class DateModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val dayName: String,
    val day: Byte,
    val month: String,
)
