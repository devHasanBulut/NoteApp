package com.example.noteappui.presentation

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun AllNotes(
    mainActivityViewModel: MainActivityViewModel = MainActivityViewModel(),
    modifier: Modifier = Modifier
) {
    LaunchedEffect(true) {
        mainActivityViewModel.provideNoteList()

    }

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier
            .wrapContentSize()
            .padding(top = 25.dp),
        contentPadding = PaddingValues(16.dp),
        verticalItemSpacing = 16.dp,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        items(mainActivityViewModel.noteList) { note ->
            Notes(notesViewEntity = note)
        }

    }

}

@Composable
fun Notes(
    notesViewEntity: NoteViewEntity,
    modifier: Modifier = Modifier,
    mainActivityViewModel: MainActivityViewModel = MainActivityViewModel()
) {
    var changeTitle by remember{ mutableStateOf(notesViewEntity.title) }

    Card(modifier = modifier.wrapContentSize()) {
        Column(
            modifier = modifier.wrapContentSize()

        ) {
            TextField(
                value = changeTitle,
                onValueChange = {
                                changeTitle = it
                                },

                textStyle = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                ),
            )
            TextField(
                value = notesViewEntity.description,
                onValueChange = { newDescription ->
                    notesViewEntity.description = newDescription

                },

                modifier = Modifier.padding(start = 20.dp, top = 7.dp, bottom = 15.dp),
            )

        }

    }


}

@Composable
fun BasicButton(
    mainActivityViewModel: MainActivityViewModel = MainActivityViewModel()
) {

    if (mainActivityViewModel.buttonClicked) {
        ButtonTest {
            mainActivityViewModel.buttonClicked = false
        }
    } else {
        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(1f, false)
            ) {}

            Button(
                onClick = {
                    mainActivityViewModel.buttonClicked = true
                }, modifier = Modifier.padding(end = 2.dp)

            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add button")

            }
        }
    }
}

@Suppress("UNUSED_EXPRESSION")
@Composable
fun ButtonTest(
    mainActivityViewModel: MainActivityViewModel = MainActivityViewModel(),
    pressBack: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 20.dp)
        ) {
            TextField(
                //title
                value = mainActivityViewModel.title,
                onValueChange = {
                    mainActivityViewModel.title = it
                },
                placeholder = {
                    Text(text = "Title..")
                },
            )
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 30.dp)
        ) {

            TextField(
                value = mainActivityViewModel.description,
                onValueChange = {
                    mainActivityViewModel.description = it
                },
                placeholder = {
                    Text(text = "Description..")
                },
            )


        }
        val context = LocalContext.current

        Button(onClick = {
            mainActivityViewModel.addNewNote()
            mainActivityViewModel.buttonClicked = true
            mainActivityViewModel.provideNoteList()
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)


        }) {
            Text("Save")

        }
        if (mainActivityViewModel.buttonClicked) {
            OnClick(
                onClick = Unit,

                )

        }

        IconButton(
            onClick = { pressBack() }, modifier = Modifier.size(40.dp)
        ) {

            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back button")
        }

    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun OnClick(mainActivityViewModel: MainActivityViewModel = MainActivityViewModel(), onClick: Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        mainActivityViewModel.provideNoteList()
        withContext(Dispatchers.Main) {
            mainActivityViewModel.buttonClicked = false

        }
    }
}




data class NoteViewEntity(
    val id: Int = 0,
    var title: String,
    var description: String,
    val category: String,
    val date: String,
)