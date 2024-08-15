package com.example.noteappui.repository

import com.example.noteappui.NetworkChecker
import com.example.noteappui.data.localdatasource.NotesModel
import com.example.noteappui.data.localdatasource.NotesModelDao
import com.example.noteappui.data.remotedatasource.NotesApiService

class NoteRepository(
    private val noteDao: NotesModelDao, private val notesApiService: NotesApiService, private val networkChecker: NetworkChecker
) {
    suspend fun getNotes(): List<NotesModel> {
        return if (networkChecker.isInternetAvailable()) {
            notesApiService.getAllNotes().let { notes ->
                noteDao.insertAllNotes(notes)
                notes
            }
        } else {
            noteDao.getAllNotes()
        }
    }

    suspend fun createNote(notesModel: NotesModel) {
        if (networkChecker.isInternetAvailable()) {
            notesApiService.createNote(notesModel)
        }
        noteDao.insertNote(notesModel)
    }

    suspend fun updateNote(notesModel: NotesModel) {
        if (networkChecker.isInternetAvailable()) {
            notesApiService.updateNote(notesModel.id, notesModel)
        }
        noteDao.updateNote(notesModel)
    }

    suspend fun deleteNote(notesModel: NotesModel) {
        if (networkChecker.isInternetAvailable()) {
            notesApiService.deleteNote(notesModel.id)
        }
        noteDao.updateNote(notesModel)
    }

}