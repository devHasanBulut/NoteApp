package com.example.noteappui.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun AllDate(mainActivityViewModel: MainActivityViewModel) {
    LaunchedEffect(true) {
        mainActivityViewModel.provideDayList()
    }
    AllDatePreview(mainActivityViewModel.dayList)
}

@Preview
@Composable
private fun AllDatePreview(
    dayList: List<DateViewEntity> = listOf(
        DateViewEntity(dayName = "Mon", day = 1, month = "Jan"),
        DateViewEntity(dayName = "Tue", day = 2, month = "Feb"),
        DateViewEntity(dayName = "Wed", day = 3, month = "Mar"),
        DateViewEntity(dayName = "Thu", day = 4, month = "Apr"),
    )
) {
    val uniqueDayList = remember(dayList) {
        dayList.distinctBy { dateModel -> dateModel }
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 15.dp, start = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        item {
            uniqueDayList.forEach { dateModel ->
                Date(dateViewEntity = dateModel)

            }
        }

    }

}

@Preview
@Composable
private fun Date(
    dateViewEntity: DateViewEntity = DateViewEntity(
        dayName = "Mon", day = 1, month = "Jan"
    ), modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier.wrapContentSize()
    ) {
        Card(
            modifier = modifier
                .width(60.dp)
                .height(80.dp)
                .padding(start = 15.dp)

        ) {
            Text(
                text = dateViewEntity.day.toString(),
                modifier = modifier
                    .padding(3.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = dateViewEntity.dayName,
                modifier = modifier
                    .padding(3.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = dateViewEntity.month,
                modifier = modifier
                    .padding(3.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

data class DateViewEntity(
    val dayName: String, val day: Int, val month: String
) {
}