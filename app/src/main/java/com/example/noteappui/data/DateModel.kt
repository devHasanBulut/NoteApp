package com.example.noteappui.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity
data class DateModel(
    var date : Long
)