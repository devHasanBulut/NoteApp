package com.example.noteappui

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(){
    Row(
        modifier = Modifier.wrapContentSize()
    ) {
        var text by remember { mutableStateOf("") }
        var active by remember { mutableStateOf(false) }


        SearchBar(
            query = text,
            onQueryChange = {
                text = it

            } ,
            onSearch = {
                active = false
            },
            active = active,
            onActiveChange = {
                active = it
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
