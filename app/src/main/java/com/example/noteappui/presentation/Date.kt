package com.example.noteappui.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Suppress("ktlint:compose:modifier-missing-check", "ktlint:standard:function-naming")
@Composable
fun AllDate(mainActivityViewModel: MainActivityViewModel) {
    LaunchedEffect(true) {
        mainActivityViewModel.getAllDateFromMySQL()
        Log.d(
            "ALL DATE",
            "test launched effect: ${mainActivityViewModel.getAllNotesFromMySQL()}",
        )
    }
    val uniqueDayList = mainActivityViewModel.dateListForMySql.distinctBy { it }
    Log.d("test unique", "uniqueDayList: $uniqueDayList")

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 15.dp, start = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(uniqueDayList) { dateModel ->
            Date(dateViewEntity = dateModel)
        }
    }
}

@Suppress("ktlint:compose:param-order-check")
@Preview
@Composable
private fun Date(
    dateViewEntity: DateViewEntity = DateViewEntity(
        dayName = "Mon",
        day = 1,
        month = "Jan",
    ),
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    selectedDate: DateViewEntity? = null,
    onDateSelected: (DateViewEntity) -> Unit = {},
) {
    Row(
        modifier = modifier.wrapContentSize(),
    ) {
        Card(
            modifier = modifier
                .width(60.dp)
                .height(80.dp)
                .padding(start = 15.dp)
                .clickable { onDateSelected(dateViewEntity) },
        ) {
            Text(
                text = dateViewEntity.day.toString(),
                modifier = modifier
                    .padding(3.dp)
                    .align(Alignment.CenterHorizontally),
            )
            Text(
                text = dateViewEntity.dayName,
                modifier = modifier
                    .padding(3.dp)
                    .align(Alignment.CenterHorizontally),
            )
            Text(
                text = dateViewEntity.month,
                modifier = modifier
                    .padding(3.dp)
                    .align(Alignment.CenterHorizontally),
            )
        }
    }
}

@Suppress("ktlint:compose:modifier-missing-check", "ktlint:standard:function-naming")
@Composable
fun NewColumnContent(mainActivityViewModel: MainActivityViewModel = MainActivityViewModel()) {
    if (mainActivityViewModel.dateClicked) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow),
        ) {}
    }
}

data class DateViewEntity(
    val dayName: String,
    val day: Int,
    val month: String,
)
