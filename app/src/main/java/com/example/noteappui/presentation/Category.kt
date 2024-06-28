package com.example.noteappui.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun Category(
    categoryViewEntity: CategoryViewEntity,
    modifier: Modifier = Modifier,
    clickItem: (String) -> Unit
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
                .clickable {
                    clickItem(categoryViewEntity.category)
                }
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
            Category(categoryViewEntity = category){
                mainActivityViewModel.selectedCategory = it
            }

            }
        }

    }


data class CategoryViewEntity(
    val category: String
)
