package com.example.noteappui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteappui.ui.theme.NoteAppUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppUITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    //modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SearchBar()
                    AllDate()
                    AllCategory()
                    AllNotes()
                }
            }
        }
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    var searchText by remember {
        mutableStateOf("Search fot notes..")
    }

    Card(
        modifier = modifier
            .width(320.dp)
            .height(30.dp)
            .offset(y = 60.dp)
            .padding(start = 20.dp),

        ) {
        Row(
            modifier = modifier
                .fillMaxSize()

        ) {
            val image = painterResource(R.drawable.role_model)

            Image(
                painter = image,
                contentDescription = null,
                modifier = modifier
                    .size(50.dp)
                    .offset(10.dp)
            )

            Text(
                text = searchText,
                modifier = modifier
                    .fillMaxSize()
                    .offset(20.dp, 0.dp),

                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

//            TextField(
//                value = searchText,
//                onValueChange = {searchText = it},
//                modifier = modifier
//                    .fillMaxSize()
//                    .offset(20.dp, 0.dp),
//
//                textStyle = TextStyle(
//                    fontSize = 22.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.White
//               )
//            )

        }
    }
    val notificationImage = painterResource(R.drawable.notification__1_)
    Image(
        painter = notificationImage,
        contentDescription = null,
        modifier = modifier
            .offset(340.dp, 58.dp)
            .size(30.dp)
    )
}

@Composable
fun Date(
    dayName: String,
    day: Byte,
    month: String,
    offsetX: Int,
    offsetY: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Card(
            modifier = modifier
                .offset(offsetX.dp, offsetY.dp)
                .width(40.dp)
                .height(80.dp)

        ) {
            Column(
                modifier = modifier
                    .fillMaxHeight(),
                //verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(text = dayName, modifier = modifier.padding(3.dp))
                Text(text = day.toString(), modifier = modifier.padding(3.dp))
                Text(text = month, modifier = modifier.padding(3.dp))
                //texti değiştirilebilir text yap

            }
        }
    }
}

@Composable
fun AllDate() {
    Date("Tue", 23, "Apr", 20, 120)
    Date("Wed", 24, "Apr", 70, 120)
    Date("Thu", 25, "Apr", 120, 120)
    Date("Fri", 26, "Apr", 170, 120)
    Date("Sat", 27, "Apr", 220, 120)
    Date("Sun", 28, "Apr", 270, 120)
}

@Composable
fun Category(
    categoryName: String,
    offsetX: Int,
    offsetY: Int,
    width: Int,
    startPadding: Int,
    modifier: Modifier = Modifier

) {
    Card(
        modifier = modifier
            .offset(offsetX.dp, offsetY.dp)
            .width(width.dp)
            .height(27.dp)

    ) {
        Text(
            text = categoryName,
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = startPadding.dp)

        )
    }
}

@Composable
fun AllCategory() {
    Category("All", 10, 230, 30, startPadding = 3)
    Category("Important", 50, 230, 100, startPadding = 5)
    Category("Lecture Notes", 160, 230, 120, 8)
    Category("To-do List", 290, 230, 100, 5)
}

//compose grit

@Composable
fun Notes(
    title: String, note: String, height: Int, color: Color,
    offsetX: Int, offsetY: Int, modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .offset(offsetX.dp, offsetY.dp)
            .width(220.dp)
            .height(height.dp)

    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color)

        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = modifier
                    .padding(start = 15.dp, top = 10.dp)
            )
            Text(
                text = note,
                modifier = modifier
                    .padding(start = 20.dp, top = 7.dp)
            )

        }
    }
}

@Composable
fun AllNotes(modifier: Modifier = Modifier) {

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .height(290.dp)
            .offset(y = 280.dp)
            //.background(Color.Green)
        ,
        contentPadding = PaddingValues(start = 50.dp)
    ){
        item {
            Notes(title = "Product Meeting", note = "1. Review of Previous Action Items" +
                    "\n2. Product Development Update\n3. User Feedback and Customer Insights" +
                    "\n4. Competitive Analysis\n5. Roadmap Discussion" ,
                height = 290 , color = Color.Blue.copy(0.15f) , offsetX = -40 , offsetY = 0 )
        }
        item {
            Notes(title = "To-Do List", note = "1. Reply to emails\n2. Prepare presentation slides for the marketing meeting" +
                    "\n3. Conduct research on competitor products\n4. Schedule and plan customer interviews" +
                    "\n5. take a break and recharge",
                height = 290 , color = Color.Magenta.copy(0.1f), offsetX = -20 , offsetY = 0 )
        }

    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 585.dp)
            //.background(Color.Green)
        ,
        contentPadding = PaddingValues(top = 80.dp)
    ){
        item { 
            Notes(title = "Shopping List", note = "1. Rice\n2. Pasta\n3. Cereal\n4. Yogurt" +
                    "\n5. Cheese\n6. Butter", height =200 , color = Color.Yellow.copy(0.15f) ,
                offsetX = 10, offsetY = -60)
        }
        item {
            Notes(title = "Notes", note = "Share insights and\nfinding from\nrecent competitive analysis" ,
                height = 160 , color = Color(0xFFFF6F00).copy(0.12f) , offsetX = 10 , offsetY = -30 )
        }

    }

}
//calender sonraki x gün parametre olacak ikisi de yana kaydıracak şekilde (date kısmı)
//uı state nedir(data class olcak)


