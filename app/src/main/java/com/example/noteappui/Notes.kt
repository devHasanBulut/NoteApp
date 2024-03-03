import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
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
import com.example.noteappui.CategoryModel
import com.example.noteappui.DateModel
import com.example.noteappui.MainActivity
import com.example.noteappui.NotesModel
import com.example.noteappui.NotesModelDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

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
            .wrapContentSize()
            .padding(top = 25.dp),
        contentPadding = PaddingValues(16.dp),
        verticalItemSpacing = 16.dp,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        items(noteList) { notesModel ->
            Notes(notesModel = notesModel, notesModelDao)

        }

    }

}

@Composable
fun Notes(
    notesModel: NotesModel,
    notesModelDao: NotesModelDao,
    modifier: Modifier = Modifier
) {
    var changeTitle by remember {
        mutableStateOf(notesModel.title)
    }
    var changeDescription by remember {
        mutableStateOf(notesModel.description)
    }
//    var changeTitle by remember { mutableStateOf("") }
//    var changeDescription by remember { mutableStateOf("") }

    Card(modifier = modifier.wrapContentSize()) {
        Column(
            modifier = modifier
                .wrapContentSize()

        ) {
            TextField(
                value = changeTitle, onValueChange = { changeTitle = it },
                textStyle = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                ),
            )
            TextField(
                value = changeDescription, onValueChange = { changeDescription = it },
                modifier = Modifier
                    .padding(start = 20.dp, top = 7.dp, bottom = 15.dp),
            )

        }

    }

}

@Composable
fun BasicButton(notesModelDao: NotesModelDao, notesModel: NotesModel) {
    var buttonClicked by remember { mutableStateOf(false) }
    if (buttonClicked) {
        ButtonTest(notesModelDao, notesModel) {
            buttonClicked = false
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
                    buttonClicked = true
                },
                modifier = Modifier
                    .padding(end = 2.dp)

            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add button")

            }
        }
    }
}

@Suppress("UNUSED_EXPRESSION")
@Composable
fun ButtonTest(notesModelDao: NotesModelDao, notesModel: NotesModel, pressBack: () -> Unit) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var buttonClicked by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 20.dp)
        ) {
            TextField(
                //title
                value = title,
                onValueChange = {
                    title = it
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
            //description
            TextField(
                value = description,
                onValueChange = {
                    description = it
                },
                placeholder = {
                    Text(text = "Description..")
                },
            )


        }
        val context = LocalContext.current

        Button(onClick = {
            buttonClicked = true
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)


        }) {
            Text("Save")

        }
        if (buttonClicked) {
            OnClick(
                notesModel = NotesModel(title = title, description = description, category = title),
                notesModelDao,
                categoryModel = CategoryModel(category = title),
                dateModel = DateModel(date = Calendar.getInstance().timeInMillis)

            )

        }

        IconButton(
            onClick = { pressBack() },
            modifier = Modifier
                .size(40.dp)
        ) {

            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back button")
        }

    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun OnClick(
    notesModel: NotesModel,
    notesModelDao: NotesModelDao,
    categoryModel: CategoryModel,
    dateModel: DateModel
) {

    CoroutineScope(Dispatchers.IO).launch {
        insertNote(notesModel, notesModelDao, dateModel)

    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun UpdateNotes(
    notesModel: NotesModel,
    notesModelDao: NotesModelDao
) {
    CoroutineScope(Dispatchers.IO).launch {
        updateNote(notesModel, notesModelDao)
    }
}

suspend fun updateNote(note: NotesModel, notesModelDao: NotesModelDao) {
    withContext(Dispatchers.IO) {
        notesModelDao.updateNote(note)
        Log.d("Main Activity", "Note updated: $note")
    }
}


suspend fun insertNote(note: NotesModel, notesModelDao: NotesModelDao, dateModel: DateModel) {
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

suspend fun getAllNotes(notesModelDao: NotesModelDao): List<NotesModel> {
    return withContext(Dispatchers.IO) {
        val notes = notesModelDao.getAllNotes()
        Log.d("MainActivity", "Retrieved notes: $notes")
        notes
    }
}
