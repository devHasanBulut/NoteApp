package com.example.noteappui.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappui.Dependencies.notesModelDao
import com.example.noteappui.domain.GetCategoryViewEntityUseCase
import com.example.noteappui.domain.GetDateViewEntityUseCase
import com.example.noteappui.domain.GetNotesViewEntityUseCase
import com.example.noteappui.domain.InsertNote
import com.example.noteappui.repository.InsertNoteFb
import com.example.noteappui.repository.ReadCategoryFirebase
import com.example.noteappui.repository.ReadDateFirebase
import com.example.noteappui.repository.ReadNotesFirebase
import com.example.noteappui.repository.UpdateNote
import com.example.noteappui.repository.updateNoteFirebase
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

    var noteListFirebase by mutableStateOf(emptyList<NoteViewEntity>())

    var categoryListFirebase by mutableStateOf(emptyList<CategoryViewEntity>())

    var dateListFirebase by mutableStateOf(emptyList<DateViewEntity?>())

    fun onDateClicked(clicked: Boolean) {
        dateClicked = clicked
    }

    fun provideDateListFirebase() {
        @Suppress("ktlint:standard:multiline-expression-wrapping")
        getDateFromFirebase.readDateFirebase { firebaseDate ->
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
            categoryListFirebase =
                firebaseCategory.map {
                    CategoryViewEntity(
                        category = it.category,
                    )
                }
        }
    }

    fun provideNoteListForFirebase() {
        getNotesFromFirebase.readNotesFirebase { firebaseNotes ->
            noteListFirebase =
                firebaseNotes.map {
                    NoteViewEntity(
                        id = 0,
                        title = it.title,
                        description = it.description,
                        category = it.category,
                        date = it.date.toString(),
                    )
                }
        }
    }

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

    private var updateNoteFirebase = UpdateNote()
    private var insertNoteUseCase = InsertNote()
    private var insertNoteUseCaseFb = InsertNoteFb()

    fun updateNote(
        noteId: String,
        newTitle: String,
        newDescription: String
    ){
        viewModelScope.launch(Dispatchers.IO) {
            updateNoteFirebase.updateNote(
                noteId = noteId,
                title = newTitle,
                description = newDescription,
                category = newTitle,
                date = System.currentTimeMillis()
            )
            noteListFirebase = noteListFirebase.map {
                if (it.id.toString() == noteId){
                    it.copy(title = newTitle, description = newDescription)
                } else{
                    it
                }
            }
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
