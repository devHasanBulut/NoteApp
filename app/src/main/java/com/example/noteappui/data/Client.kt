package com.example.noteappui.data

import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.platform.LocalContext
import com.example.noteappui.CheckNetConnect
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.coroutineContext


object RetrofitClient {
    private const val BASE_URL = "http://192.168.1.35:8080/"

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: NotesApiService by lazy {
        instance.create(NotesApiService::class.java)
    }
}

