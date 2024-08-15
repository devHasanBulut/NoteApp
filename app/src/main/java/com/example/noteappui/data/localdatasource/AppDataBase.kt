package com.example.noteappui.data.localdatasource

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NotesModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notesModelDao(): NotesModelDao
}
