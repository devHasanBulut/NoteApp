package com.example.noteappui

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface CategoryModelDao {
    @Query("SELECT categoryModel FROM notes")
    fun getAllCategory(): List<CategoryModel>

    @Insert
    fun insertCategory(categoryModel: CategoryModel)

    @Delete
    fun deleteCategory(categoryModel: CategoryModel)
}