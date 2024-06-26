package com.example.noteappui.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
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
import com.example.noteappui.data.CategoryModel
import com.example.noteappui.data.NotesModelDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun Category(
    categoryViewEntity: CategoryViewEntity,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .wrapContentWidth()
            .height(27.dp)
            .padding(end = 5.dp)

    ) {
        Text(
            text = categoryViewEntity.category,
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 10.dp, end = 10.dp, top = 3.dp)

        )
    }
}


@Composable
fun AllCategory(
    mainActivityViewModel: MainActivityViewModel = MainActivityViewModel(),

) {
    LaunchedEffect(true){
        mainActivityViewModel.provideCategoryList()
    }

    LazyRow(
        modifier = Modifier
            .wrapContentSize()
            .padding(top = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        items(mainActivityViewModel.categoryList) { category ->
            Category(categoryViewEntity = category)

            }
        }

    }


data class CategoryViewEntity(
    val category: String
)
