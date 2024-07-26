package com.example.noteappui.repository

import android.util.Log
import com.example.noteappui.presentation.DateViewEntity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.Calendar
import java.util.Locale

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

class ReadCategoryFirebase {
    fun readCategoryFirebase(callback: (List<CategoryModelForFirebase>) -> Unit) {
        val categoryListFirebase = mutableListOf<CategoryModelForFirebase>()
        database.child(pathName).addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (categorySnapshot in snapshot.children) {
                        val category =
                            categorySnapshot.getValue(CategoryModelForFirebase::class.java)
                        category?.let {
                            categoryListFirebase.add(it)
                        }
                    }
                    callback(categoryListFirebase)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            },
        )
    }
}

class ReadDateFirebase {
    fun readDateFirebase(callback: (List<DateViewEntity>) -> Unit) {
        database.child(pathName).addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val dateListFirebase = mutableListOf<DateViewEntity>()
                    for (dateSnapshot in snapshot.children) {
                        val date = dateSnapshot.getValue(DateModelForFirebase::class.java)
                        val res=
                            date?.let {
                                val calendar = Calendar.getInstance()
                                calendar.timeInMillis = it.date
                                val dayName =
                                    calendar.getDisplayName(
                                        Calendar.DAY_OF_WEEK,
                                        Calendar.SHORT,
                                        Locale.getDefault(),
                                    )
                                val day = calendar.get(Calendar.DAY_OF_MONTH)
                                val month =
                                    calendar.getDisplayName(
                                        Calendar.MONTH,
                                        Calendar.SHORT,
                                        Locale.getDefault(),
                                    )
                                DateViewEntity(dayName = dayName, day = day, month = month)
                            }
                        dateListFirebase.add(res!!)
                    }
                    Log.d("FirebaseDates", "test database: $dateListFirebase")
                    callback(dateListFirebase)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("FirebaseDates", "test error", error.toException())
                }
            },
        )
    }
}

class InsertNoteFb {
    private fun execute(newNote: NotesModelForFirebase) {
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
