package com.example.lab5_plataformas.pantallas.ui.meals.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.example.lab5_plataformas.pantallas.ui.meals.repository.MealListRepository


@Composable
fun MealListScreen(category: String) {
    val viewModel = remember { MealListViewModel(MealListRepository()) }
    val meals = viewModel.meals.collectAsState(initial = emptyList())

    // Llamamos a fetchMealsByCategory al inicio
    remember { viewModel.fetchMealsByCategory(category) }

    // Renderizamos la lista de comidas usando Jetpack Compose
    meals.value?.forEach { meal ->
        Text(text = meal.name)
    }
}
