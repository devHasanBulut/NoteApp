package com.example.noteappui.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappui.Dependencies.notesModelDao
import com.example.noteappui.data.CategoryModel
import com.example.noteappui.domain.GetDateViewEntityUseCase
import com.example.noteappui.domain.GetNotesViewEntityUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel(){
    var dayList by mutableStateOf(emptyList<DateViewEntity>())


    var text by mutableStateOf("")
        private set
    var active by mutableStateOf(false)
        private set

    var changeTitle by mutableStateOf("Title")

    var buttonClicked by mutableStateOf(false)

    var title by mutableStateOf("")

    var description by mutableStateOf("")

    var changeDescription by mutableStateOf("description")

    var noteList by mutableStateOf(emptyList<NoteViewEntity>())


    var categoryList by mutableStateOf(emptyList<CategoryModel>())

    fun provideNoteList() {
        viewModelScope.launch(Dispatchers.IO) {
            notesModelDao?.let {
                noteList = GetNotesViewEntityUseCase(it).execute()
            }
        }
    }


    fun provideDayList() {
        viewModelScope.launch(Dispatchers.IO) {
            notesModelDao?.let {
                dayList = GetDateViewEntityUseCase(it).execute()
            }
        }
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

    fun onButtonClicked() {
        buttonClicked = true
    }





}


