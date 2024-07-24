package com.example.noteappui.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private val database: DatabaseReference = FirebaseDatabase.getInstance().reference
val pathName = "notes"

class ReadNotesFirebase {
    fun readNotesFirebase(callback: (List<NotesModelForFirebase>) -> Unit) {
        val noteListFirebase = mutableListOf<NotesModelForFirebase>()
        database.child(pathName).addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (noteSnapshot in snapshot.children) {
                        val note = noteSnapshot.getValue(NotesModelForFirebase::class.java)
                        note?.let {
                            noteListFirebase.add(it)
                        }
                    }
                    callback(noteListFirebase)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            },
        )
    }
}

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
