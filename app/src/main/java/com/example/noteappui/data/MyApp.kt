package com.example.noteappui.data

import android.app.Application
import com.example.noteappui.Dependencies

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Dependencies.init(this)
    }
}