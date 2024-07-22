package com.example.noteappui.repository

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private val database: DatabaseReference = FirebaseDatabase.getInstance().reference
val pathName = "notes"

class InsertNoteFb {
    fun execute(newNote: NotesModelForFirebase) {
        insertNoteFb(newNote)
    }

    fun addNewNote(
        title: String,
        description: String,
        category: String,
        date: Long,
    ) {
        val newNote =
            NotesModelForFirebase(
                title = title,
                description = description,
                category = category,
                date = date,
            )
        execute(newNote)
    }
}

fun insertNoteFb(notesModel: NotesModelForFirebase) {
    val noteId = database.child(pathName).push().key
    noteId?.let {
        database.child(pathName).child(it).setValue(notesModel)
    }
}

@Composable
fun getNotesFromFirebase() {
    var testNote by remember {
        mutableStateOf<NotesModelForFirebase?>(null)
    }
    LaunchedEffect(Unit) {
        database.child(pathName).addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    testNote = snapshot.getValue(NotesModelForFirebase::class.java)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            },
        )
    }
}
