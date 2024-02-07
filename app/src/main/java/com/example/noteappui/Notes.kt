package com.example.noteappui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun AllNotes(
    notesModelDao: NotesModelDao,
    modifier: Modifier = Modifier

) {
    var noteList by remember { mutableStateOf(emptyList<NotesModel>()) }
    LaunchedEffect(notesModelDao) {
        noteList = getAllNotes(notesModelDao)
    }

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier
            .wrapContentSize(),
        contentPadding = PaddingValues(16.dp),
        verticalItemSpacing = 16.dp,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(noteList) { notesModel ->
            Notes(notesModel = notesModel)
        }
    }
}


suspend fun getAllNotes(notesModelDao: NotesModelDao): List<NotesModel> {
    return withContext(Dispatchers.IO) {
        val notes = notesModelDao.getAllNotes()
        Log.d("MainActivity", "Retrieved notes: $notes")
        notes
    }
}

@Composable
fun Notes(
    notesModel: NotesModel,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.wrapContentSize()) {
        Column(
            modifier = modifier
                .wrapContentSize()

        ) {
            Text(
                text = notesModel.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = modifier
                    .padding(start = 15.dp, top = 10.dp)
            )

            Text(
                text = notesModel.description,
                modifier = modifier
                    .padding(start = 20.dp, top = 7.dp, bottom = 15.dp)
            )

        }

    }

}


suspend fun insertNote(note: NotesModel, notesModelDao: NotesModelDao) {
    withContext(Dispatchers.IO) {
        notesModelDao.insertNote(note)
        Log.d("MainActivity", "Note inserted: $note")
    }
}


suspend fun deleteNotes(notes: List<NotesModel>, notesModelDao: NotesModelDao) {
    withContext(Dispatchers.IO) {
        notes.forEach { note ->
            notesModelDao.deleteNote(note)
            Log.d("MainActivity", "Note deleted: $note")
        }
    }
}
