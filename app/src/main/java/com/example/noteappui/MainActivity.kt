package com.example.noteappui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
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
import com.example.noteappui.ui.theme.NoteAppUITheme
import insertNote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


//gradle apk yı üreten şey.

class MainActivity : ComponentActivity() {

    private lateinit var notesModelDao: NotesModelDao
    private lateinit var categoryModelDao: CategoryModelDao
    private lateinit var dateModelDao: DateModelDao

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("CoroutineCreationDuringComposition", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val database = AppDatabase.getInstance(applicationContext)
        notesModelDao = database.notesModelDao()

        val categoryDatabase = AppDatabase.getInstance(applicationContext)
        categoryModelDao = categoryDatabase.categoryModelDao()

        val dateDatabase = AppDatabase.getInstance(applicationContext)
        dateModelDao = dateDatabase.dateModelDao()



        setContent {
            window.statusBarColor = getColor(R.color.black)
            NoteAppUITheme {
                MainMenu(notesModelDao,categoryModelDao, dateModelDao)

            }
        }


    }

}
