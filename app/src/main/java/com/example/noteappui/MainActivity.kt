package com.example.noteappui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.example.noteappui.ui.theme.NoteAppUITheme


//gradle apk yı üreten şey.

class MainActivity : ComponentActivity() {

    private lateinit var notesModelDao: NotesModelDao

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("CoroutineCreationDuringComposition", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val database = AppDatabase.getInstance(applicationContext)
        notesModelDao = database.notesModelDao()


        setContent {


//            val navController = rememberNavController()
//            NavHost(navController = navController, startDestination = "target screen"){
//                composable("target screen"){
//                    val receivedData = it.arguments?.getString("data")
//
//                }

            //          }
            val initialNotesModel = NotesModel(title = "", description = "", category = "")
            window.statusBarColor = getColor(R.color.black)
            NoteAppUITheme {
                MainMenu(notesModelDao, initialNotesModel)

            }
        }


    }

}

