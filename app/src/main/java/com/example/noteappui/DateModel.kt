package com.example.noteappui

import androidx.room.Entity
import java.util.Calendar

@Entity
data class DateModel(
    var date: Long
) {
    constructor(year: Int, month: Int, day: Int) : this(
        Calendar.getInstance().apply {
            set(year, month, day, 0, 0, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis
    )
}