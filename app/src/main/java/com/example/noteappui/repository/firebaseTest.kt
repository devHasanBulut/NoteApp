package com.example.noteappui.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue

val database = Firebase.database.reference

val postListener = object : ValueEventListener{
    override fun onDataChange(snapshot: DataSnapshot) {
        val post = snapshot.getValue<User>()

    }

    override fun onCancelled(databaseError: DatabaseError) {
        Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
    }

}




@IgnoreExtraProperties
data class User(
    val username: String? = null,
    val email: String? = null,
)

fun writeNewUser(
    userId: String,
    name: String,
    email: String,
) {
    val user = User(name, email)

    database.child("users").child(userId).setValue(user)


}
