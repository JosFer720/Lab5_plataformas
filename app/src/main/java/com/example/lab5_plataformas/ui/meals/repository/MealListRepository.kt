package com.example.lab5_plataformas.pantallas.ui.meals.repository

import com.example.lab5_plataformas.networking.MealsWebService
import com.example.lab5_plataformas.networking.response.MealsByCategoryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MealListRepository {
    private val webService = MealsWebService()

    suspend fun getMealsByCategory(category: String): Response<MealsByCategoryResponse> {
        return withContext(Dispatchers.IO) {
            webService.getMealsByCategory(category)
        }
    }
}