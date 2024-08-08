package com.example.noteappui.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappui.Dependencies.notesModelDao
import com.example.noteappui.data.NotesModel
import com.example.noteappui.domain.GetCategoryViewEntityUseCase
import com.example.noteappui.domain.GetDateViewEntityUseCase
import com.example.noteappui.domain.GetNotesViewEntityUseCase
import com.example.noteappui.domain.InsertNote
import com.example.noteappui.repository.InsertNoteFb
import com.example.noteappui.repository.ReadCategoryFirebase
import com.example.noteappui.repository.ReadDateFirebase
import com.example.noteappui.repository.ReadNotesFirebase
import com.example.noteappui.repository.RetrofitClient
import com.example.noteappui.repository.UpdateNote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    var dayList by mutableStateOf(emptyList<DateViewEntity>())

    var noteList by mutableStateOf(emptyList<NoteViewEntity>())

    var categoryList by mutableStateOf(emptyList<CategoryViewEntity>())

    var text by mutableStateOf("")
        private set
    var active by mutableStateOf(false)
        private set

    var buttonClicked by mutableStateOf(false)

    var title by mutableStateOf("")

    var description by mutableStateOf("")

    var dateClicked by mutableStateOf(false)

    private var getCategoryFromFirebase = ReadCategoryFirebase()

    private var getNotesFromFirebase = ReadNotesFirebase()

    private var getDateFromFirebase = ReadDateFirebase()

    var noteListForMySql by mutableStateOf(emptyList<NoteViewEntity>())

    var categoryListForMySql by mutableStateOf(emptyList<CategoryViewEntity>())

    var dateListForMySql by mutableStateOf(emptyList<DateViewEntity>())

    var noteListFirebase by mutableStateOf(emptyList<NoteViewEntity>())

    var categoryListFirebase by mutableStateOf(emptyList<CategoryViewEntity>())

    var dateListFirebase by mutableStateOf(emptyList<DateViewEntity?>())


    fun provideDateListFirebase() {
        @Suppress("ktlint:standard:multiline-expression-wrapping") getDateFromFirebase.readDateFirebase { firebaseDate ->
            dateListFirebase = firebaseDate.map {
                DateViewEntity(
                    dayName = it.dayName,
                    day = it.day,
                    month = it.month,
                )
            }
            Log.d("FirebaseDates", "Updated dateListFirebase: $dateListFirebase")
        }
    }

    fun provideCategoryListFirebase() {
        getCategoryFromFirebase.readCategoryFirebase { firebaseCategory ->
            categoryListFirebase = firebaseCategory.map {
                CategoryViewEntity(
                    category = it.category,
                )
            }
        }
    }

    /*fun provideNoteListForFirebase() {
        getNotesFromFirebase.readNotesFirebase { firebaseNotes ->
            noteListFirebase =
                firebaseNotes.map {
                    NoteViewEntity(
                        id = it.id!!.toInt(),
                        title = it.title,
                        description = it.description,
                        category = it.category,
                        date = it.date.toString(),
                    )
                }
        }
    }
    */


    fun provideDayList() {
        viewModelScope.launch(Dispatchers.IO) {
            dayList = GetDateViewEntityUseCase().execute()!!
        }
    }

    fun provideCategoryList() {
        viewModelScope.launch(Dispatchers.IO) {
            notesModelDao?.let {
                categoryList = GetCategoryViewEntityUseCase(it).execute()!!
            }
        }
    }

    fun provideNoteList() {
        viewModelScope.launch(Dispatchers.IO) {
            notesModelDao?.let {
                noteList = GetNotesViewEntityUseCase(it).execute()!!
            }
        }
    }

    fun addNewNoteMySql() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newNote = NotesModel(
                    title = title,
                    description = description,
                    category = title,
                    date = System.currentTimeMillis()
                )
                val response = RetrofitClient.api.createNote(newNote).execute()
                if (response.isSuccessful) {
                    Log.d("MainActivityViewModel", "Not başarıyla eklendi")
                    provideNoteList()
                } else {
                    Log.e("MainActivityViewModel", "Hata: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("MainActivityViewModel", "Hata: ${e.message}")
            }
        }
    }

    fun getAllNotesFromMySQL() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.api.getAllNotes().execute()
                if (response.isSuccessful) {
                    noteListForMySql = response.body()!!.map {
                        NoteViewEntity(
                            id = it.id,
                            title = it.title,
                            description = it.description,
                            category = it.category,
                            date = it.date.toString()
                        )
                    }
                } else {
                    Log.e("MainActivityViewModel", "Hata: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("MainActivityViewModel", "Hata: ${e.message}")
            }
        }
    }

    fun getAllCategoryFromMySQL(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.api.getAllCategories().execute()
                if (response.isSuccessful) {
                    categoryListForMySql = response.body()!!.map {
                        CategoryViewEntity(
                            category = it.category
                        )
                    }
                } else {
                    Log.e("MainActivityViewModel", "Hata: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("MainActivityViewModel", "Hata: ${e.message}")
            }
        }
    }

    fun getAllDateFromMySQL(){
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val response = RetrofitClient.api.getAllDates().execute()

                if (response.isSuccessful) {
                    dateListForMySql = response.body()!!.map {
                        val calendar = java.util.Calendar.getInstance()
                        calendar.timeInMillis = it.date
                        val dayName =
                            calendar.getDisplayName(
                                java.util.Calendar.DAY_OF_WEEK,
                                java.util.Calendar.SHORT,
                                java.util.Locale.getDefault(),
                            )
                        val day = calendar.get(java.util.Calendar.DAY_OF_MONTH)
                        val month =
                            calendar.getDisplayName(
                                java.util.Calendar.MONTH,
                                java.util.Calendar.SHORT,
                                java.util.Locale.getDefault(),
                            )
                        DateViewEntity(
                            dayName = dayName!!,
                            day = day,
                            month = month!!,
                        )
                    }
                } else {
                    Log.e("MainActivityViewModel", "Hata: ${response.errorBody()?.string()}")
                }
            }
        } catch (e: Exception) {
            Log.e("MainActivityViewModel", "Hata: ${e.message}")
        }

    }


    private var updateNoteFirebase = UpdateNote()
    private var insertNoteUseCase = InsertNote()
    private var insertNoteUseCaseFb = InsertNoteFb()

    fun updateNote(
        noteId: String, newTitle: String, newDescription: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            updateNoteFirebase.updateNote(
                noteId = noteId, title = newTitle, description = newDescription, category = newTitle, date = System.currentTimeMillis()
            )
            //provideNoteListForFirebase()
        }

    }

    fun addNewNoteForFb() {
        viewModelScope.launch(Dispatchers.IO) {
            insertNoteUseCaseFb.addNewNote(
                title = title,
                description = description,
                category = title,
                date = System.currentTimeMillis(),
            )
        }
    }

    fun addNewNote() {
        viewModelScope.launch(Dispatchers.IO) {
            insertNoteUseCase.addNewNote(
                title = title,
                description = description,
                category = title,
                date = System.currentTimeMillis(),
            )
            provideNoteList()
        }
    }

    fun updateNoteTitle(
        noteId: Int,
        newTitle: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val note = notesModelDao?.getNoteById(noteId)
            note?.let {
                it.title = newTitle
                notesModelDao?.updateNoteTitle(it)
                title = newTitle
            }
        }
    }

    fun updateNoteDescription(
        noteId: Int,
        newDescription: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val note = notesModelDao?.getNoteById(noteId)
            note?.let {
                it.description = newDescription
                notesModelDao?.updateNoteDescription(it)
                description = newDescription
            }
        }
    }

    fun updateNoteCategory(
        noteId: Int,
        newCategory: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val note = notesModelDao?.getNoteById(noteId)
            note?.let {
                it.category = newCategory
                notesModelDao?.updateNoteCategory(it)
            }
        }
    }

    fun onValueChangeTitle(value: String) {
        title = value
    }

    fun onQueryChange(query: String) {
        text = query
    }

    fun onSearch() {
        active = false
        title = "Search results for deneme"
    }

    fun onActiveChange(isActive: Boolean) {
        active = isActive
    }
}

