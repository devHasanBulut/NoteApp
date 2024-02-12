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
import java.util.Calendar
import java.util.Locale

@Composable
fun Date(
    dayName: String,
    day: Byte,
    month: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .wrapContentSize()
    ) {
        Card(
            modifier = modifier
                .width(55.dp)
                .height(80.dp)
                .padding(start = 15.dp)

        ) {
            Text(
                text = dayName,
                modifier = modifier
                    .padding(3.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = day.toString(),
                modifier = modifier
                    .padding(3.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = month,
                modifier = modifier
                    .padding(3.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}
@Composable
fun AllDate(dateModelDao: DateModelDao) {
    var dayList by remember {
        mutableStateOf(emptyList<DateModel>())
    }
    LaunchedEffect(dateModelDao){
        dayList = getAllDate(dateModelDao)
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 15.dp, start = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        items(dayList) { item ->
            Date(item.dayName, item.day, item.month)
        }

    }

}


suspend fun insertDate(date: DateModel, dateModelDao: DateModelDao) {
    withContext(Dispatchers.IO) {
        dateModelDao.insertDate(date)
        Log.d("MainActivity", "Note inserted: $date")
    }
}


suspend fun deleteDate(date: List<DateModel>, dateModelDao: DateModelDao) {
    withContext(Dispatchers.IO) {
        date.forEach { date ->
            dateModelDao.deleteDate(date)
            Log.d("MainActivity", "Note deleted: $date")
        }
    }
}
suspend fun getAllDate(dateModelDao: DateModelDao): List<DateModel> {
    return withContext(Dispatchers.IO) {
        val date = getAllDate(dateModelDao)
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
