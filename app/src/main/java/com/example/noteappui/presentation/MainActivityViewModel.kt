package com.example.noteappui.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappui.domain.GetDateViewEntityUseCase
import com.example.noteappui.domain.GetNotesViewEntityUseCase
import com.example.noteappui.domain.InsertNoteUseCase
import com.example.noteappui.domain.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesViewEntityUseCase,
    private val insertNoteUseCase: InsertNoteUseCase,
    private val getDateViewEntityUseCase: GetDateViewEntityUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
) : ViewModel(
) {

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


    fun provideNoteList() {
        viewModelScope.launch(Dispatchers.IO) {
            noteList = getNotesUseCase.execute().also {
                categoryList = noteList.map {
                    CategoryViewEntity(
                        category = it.category
                    )
                }.distinct()
                dayList = getDateViewEntityUseCase.execute(noteList).distinct()
            }
        }
    }

    fun insertNote() {
        viewModelScope.launch(Dispatchers.IO) {
            insertNoteUseCase.execute(title, description)
        }
    }

    fun updateNote(noteViewEntity: NoteViewEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            updateNoteUseCase.execute(noteViewEntity)
        }
    }

    fun onQueryChange(it: String) {

    }

    fun onSearch() {
    }

    fun onActiveChange(b: Boolean) {

    }

}