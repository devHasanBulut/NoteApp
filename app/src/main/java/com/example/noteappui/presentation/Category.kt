@file:Suppress("UNUSED_EXPRESSION")

package com.example.noteappui.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun Category(
    categoryViewEntity: CategoryViewEntity,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .wrapContentWidth()
            .height(27.dp)
            .padding(end = 5.dp)
            .clickable { onClick }

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
    LaunchedEffect(true) {
        mainActivityViewModel.provideCategoryList()
    }

    var selectedCategory: CategoryViewEntity? by remember { mutableStateOf(null) }

    LazyRow(
        modifier = Modifier
            .wrapContentSize()
            .padding(top = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        item {
            mainActivityViewModel.categoryList.forEach { category ->
                Category(categoryViewEntity = category, onClick = { selectedCategory = category })

            }

        }
//        items(mainActivityViewModel.categoryList) { category ->
//            Category(categoryViewEntity = category)
//
//        }
    }
    selectedCategory?.let { category ->
        AlertDialog(
            onDismissRequest = { selectedCategory = null },
            title = { Text(text = "Category: ${category.category}") },
            confirmButton = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    mainActivityViewModel.noteList.forEach { note ->
                        Text(text = note.description)
                    }

                }
            },
            dismissButton = {
                TextButton(onClick = { selectedCategory = null }) {
                    Text(text = "Back")

                }
            },


            )
    }

}




data class CategoryViewEntity(
    val category: String
)


