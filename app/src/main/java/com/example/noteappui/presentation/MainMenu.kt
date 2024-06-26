package com.example.noteappui.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun MainMenu(modifier: Modifier = Modifier, mainActivityViewModel: MainActivityViewModel) {


    Column(
        modifier = modifier.fillMaxSize(),

        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        BasicButton()
        MainScreen(mainActivityViewModel = mainActivityViewModel)
        AllDate(mainActivityViewModel = mainActivityViewModel)
        AllCategory()
        AllNotes()

    }
}


