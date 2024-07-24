package com.example.noteappui.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NotesModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notesModelDao(): NotesModelDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                val instance =
                    Room
                        .databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            "app_database",
                        ).build()
                INSTANCE = instance
                instance
            }
    }
}
