package com.example.noteappui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class CategoryModel(
    val categoryName: String
)
val categoryList: List<CategoryModel> = mutableListOf(
    CategoryModel("All"),
    CategoryModel("Important"),
    CategoryModel("Lecture Notes"),
    CategoryModel("To- Do List"),
//    CategoryModel("To- Do List"),
//    CategoryModel("To- Do List"),
//    CategoryModel("To- Do List"),
//    CategoryModel("To- Do List"),
//    CategoryModel("To- Do List"),
)
@Composable
fun Category(
    categoryModel: CategoryModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .wrapContentWidth()
            .height(27.dp)
            .padding(end = 5.dp)

    ) {
        Text(
            text = categoryModel.categoryName,
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 10.dp, end = 10.dp, top = 3.dp)

        )
    }
}

@Composable
fun AllCategory() {

    LazyRow(
        modifier = Modifier
            .wrapContentSize()
            .padding(top = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        item {
            categoryList.forEach { categoryModel ->
                Category(categoryModel = categoryModel)

            }
        }

    }
}
