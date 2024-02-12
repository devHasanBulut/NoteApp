package com.example.noteappui

import AllNotes
import BasicButton
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview



@Composable
fun MainMenu(notesModelDao: NotesModelDao, categoryModelDao: CategoryModelDao,dateModelDao: DateModelDao,modifier: Modifier = Modifier) {


    Column(
        modifier = modifier
            .fillMaxSize(),

        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        BasicButton(notesModelDao, categoryModelDao)
        MainScreen()
        AllDate(dateModelDao)
        AllCategory(categoryModelDao)
        AllNotes(notesModelDao = notesModelDao)

    }
}


