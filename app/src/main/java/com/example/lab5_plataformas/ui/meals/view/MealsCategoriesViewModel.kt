package com.example.lab5_plataformas.ui.meals.view

import androidx.lifecycle.ViewModel
import com.example.lab5_plataformas.networking.response.MealsCategoriesResponse
import com.example.lab5_plataformas.ui.meals.repository.MealsRepository

class MealsCategoriesViewModel (private val repository: MealsRepository = MealsRepository()): ViewModel() {

    fun getMeals(successCallback: (response: MealsCategoriesResponse?) -> Unit) {
        repository.getMeals { response ->
            successCallback(response)
        }
    }
}