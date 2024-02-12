package com.example.noteappui

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface DateModelDao {
    @Query("SELECT * FROM date")
    fun getAllDate(): List<DateModel>

    @Insert
    fun insertDate(dateModel: DateModel)

    @Delete
    fun deleteDate(dateModel: DateModel)
}