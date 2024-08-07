package com.example.noteappui.presentation

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

    fun onDateClicked(clicked: Boolean){
        dateClicked = clicked
    }


    fun provideNoteList() {
        viewModelScope.launch(Dispatchers.IO) {
            notesModelDao?.let {
                noteList = GetNotesViewEntityUseCase(it).execute()!!

            }
        }
    }


    private var insertNoteUseCase = InsertNote()

    fun addNewNote() {
        viewModelScope.launch(Dispatchers.IO) {
            insertNoteUseCase.addNewNote(
                title = title,
                description = description,
                category = title,
                date = System.currentTimeMillis()
            )
            provideNoteList()
        }
    }



    fun provideCategoryList() {
        viewModelScope.launch(Dispatchers.IO) {
            notesModelDao?.let {
                categoryList = GetCategoryViewEntityUseCase(it).execute()!!
            }

        }
    }

    fun updateNoteTitle(noteId: Int, newTitle: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val note = notesModelDao?.getNoteById(noteId)
            note?.let {
                it.title = newTitle
                notesModelDao?.updateNoteTitle(it)
                title = newTitle

            }

        }
    }

    fun updateNoteDescription(noteId: Int, newDescription: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val note = notesModelDao?.getNoteById(noteId)
            note?.let {
                it.description = newDescription
                notesModelDao?.updateNoteDescription(it)
                description = newDescription
            }
        }
    }

    fun updateNoteCategory(noteId: Int, newCategory: String){
        viewModelScope.launch(Dispatchers.IO) {
            val note = notesModelDao?.getNoteById(noteId)
            note?.let {
                it.category = newCategory
                notesModelDao?.updateNoteCategory(it)
            }
        }

    }


    fun provideDayList() {
        viewModelScope.launch(Dispatchers.IO) {
            dayList = GetDateViewEntityUseCase().execute()!!

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

