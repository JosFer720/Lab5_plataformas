package com.example.lab5_plataformas.ui.meals.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.lab5_plataformas.networking.response.MealResponse

@Composable
fun MealsCategoriesScreen(
    viewModel: MealsCategoriesViewModel,
    onCategorySelected: (String) -> Unit
) {
    val mealsCategories = viewModel.mealsCategories.collectAsState()

    LazyColumn {
        items(mealsCategories.value) { meal ->
            Text(
                text = meal.name,
                modifier = androidx.compose.ui.Modifier.clickable {
                    onCategorySelected(meal.id)  // Llama a la función cuando se selecciona un elemento
                }
            )
        }
    }
}
