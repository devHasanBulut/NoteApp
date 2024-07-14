package com.example.noteappui.repository
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database


val pathName = "user"
val database = Firebase.database.reference

@IgnoreExtraProperties
data class User(
    val username: String = "",
    val email: String = ""
)

fun writeNewUser() {
    val newUser = User("hasan", "hasanbulut@")

    database.child(pathName).setValue(newUser)
}

@Composable
fun UserScreen() {
    val database = Firebase.database.reference
    var user by remember { mutableStateOf<User?>(null) }

    LaunchedEffect(Unit) {
        database.child("user").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                user = snapshot.getValue(User::class.java)
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
    user?.let { user ->
        Column {
            Text(text = "Username: ${user.username}")
            Text(text = "Email: ${user.email}")
        }
    } ?: run {
        Text(text = "No user data available")
    }
}



