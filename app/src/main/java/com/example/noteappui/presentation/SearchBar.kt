package com.example.noteappui.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    mainActivityViewModel: MainActivityViewModel = MainActivityViewModel()
){
    Row(
        modifier = Modifier.wrapContentSize()
    ) {

        SearchBar(
            query = mainActivityViewModel.text,
            onQueryChange = {
                mainActivityViewModel.onQueryChange(it)
            } ,
            onSearch = {
                mainActivityViewModel.onSearch()
            },
            active = mainActivityViewModel.active,
            onActiveChange = {
                mainActivityViewModel.onActiveChange(false)
            },
            placeholder = {
                Text(text = "Search for notes..")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search bar icon ")
            }
        ) {

        }
    }
}
