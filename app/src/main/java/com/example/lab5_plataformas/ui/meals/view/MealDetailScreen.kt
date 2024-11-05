package com.example.lab5_plataformas.pantallas.ui.meals.view

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.example.lab5_plataformas.networking.MealsWebService
import com.example.lab5_plataformas.pantallas.ui.meals.repository.MealDetailRepository

@Composable
fun MealDetailScreen(mealId: String) {
    // Crear el ViewModel manualmente y recordarlo
    val viewModel = remember { MealDetailViewModel(MealDetailRepository(MealsWebService().api)) }
    val mealDetail = viewModel.mealDetail.collectAsState(initial = null)

    // Ejecuta fetchMealDetails solo cuando mealId cambia
    LaunchedEffect(mealId) {
        viewModel.fetchMealDetails(mealId)
    }

    // Renderiza los detalles de la comida si están disponibles
    mealDetail.value?.let { meal ->
        Text(text = meal.name)
        Text(text = meal.instructions)
    }
}
