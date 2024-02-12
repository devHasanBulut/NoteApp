package com.example.noteappui

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryModel (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val categoryName: String
)

