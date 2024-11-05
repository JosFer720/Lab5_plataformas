package com.example.lab5_plataformas.pantallas.ui.meals.repository

import com.example.lab5_plataformas.networking.api.MealsApi
import com.example.lab5_plataformas.networking.response.MealDetailsResponse
import retrofit2.Response

class MealDetailRepository(private val api: MealsApi) {
    suspend fun getMealDetails(mealId: String): Response<MealDetailsResponse> {
        return api.getMealDetails(mealId)
    }
}
