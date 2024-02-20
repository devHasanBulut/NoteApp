package com.example.noteappui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.time.Duration.Companion.days

@Composable
fun Date(
    dateModel: DateModel,
    modifier: Modifier = Modifier
) {
    dateModel.date = System.currentTimeMillis()
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = dateModel.date

    val dayName = calendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.SHORT,Locale.getDefault())
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val month = calendar.getDisplayName(Calendar.MONTH,Calendar.SHORT, Locale.getDefault())

    calendar.add(Calendar.MONTH, 1)

    Row(
        modifier = modifier
            .wrapContentSize()
    ) {
        Card(
            modifier = modifier
                .width(60.dp)
                .height(80.dp)
                .padding(start = 15.dp)

        ) {
            Text(
                text = day.toString(),
                modifier = modifier
                    .padding(3.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = dayName!!,
                modifier = modifier
                    .padding(3.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = month!!,
                modifier = modifier
                    .padding(3.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}
@Composable
fun AllDate(notesModelDao: NotesModelDao) {
    var dayList by remember {
        mutableStateOf(emptyList<DateModel>())
    }
    LaunchedEffect(notesModelDao){
        if (dayList.isEmpty()){
            dayList = getAllDate(notesModelDao)
        }
    }

val uniqueDayList = remember(dayList){
    dayList.distinctBy { it.date.days }
}

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 15.dp, start = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        item{
            uniqueDayList.forEach(){ dateModel ->
                Date(dateModel = dateModel)

            }
        }

    }

}
suspend fun getAllDate(notesModelDao: NotesModelDao): List<DateModel> {
    return withContext(Dispatchers.IO) {
        val date = notesModelDao.getAllDate()
        Log.d("MainActivity", "Retrieved notes: $date")
        date
    }
}

//fun getMockDayList(): List<DateModel> {
//    val currentCalendar = Calendar.getInstance()
//    val dateModelList = mutableListOf<DateModel>()
//
//    for (i in 0..7) {
////        val days = arrayOf("SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY")
////        val dayName = days.get(Calendar.DAY_OF_WEEK-1)
//        val dayName = currentCalendar.getDisplayName(
//            Calendar.DAY_OF_WEEK,
//            Calendar.SHORT,
//            Locale.getDefault()
//        )
//        val day = currentCalendar.get(Calendar.DAY_OF_MONTH)
//        val month =
//            currentCalendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault())
//        dateModelList.add(DateModel(dayName, day.toByte(), month!!))
//        currentCalendar.add(Calendar.DAY_OF_YEAR, 1)
//    }
//    return dateModelList
//}
