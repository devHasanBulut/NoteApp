package com.example.noteappui.presentation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.noteappui.R
import com.example.noteappui.ui.theme.NoteAppUITheme
import dagger.hilt.android.AndroidEntryPoint


//gradle apk yı üreten şey.

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("CoroutineCreationDuringComposition", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContent {
            window.statusBarColor = getColor(R.color.black)
            NoteAppUITheme {
                MainMenu(Modifier, mainActivityViewModel)
            }
        }
    }

}