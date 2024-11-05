package com.example.lab5_plataformas.pantallas.ui.meals.repository

import com.example.lab5_plataformas.networking.MealsWebService
import com.example.lab5_plataformas.networking.api.MealsApi
import com.example.lab5_plataformas.networking.response.MealDetailsResponse
import retrofit2.Response

class MealDetailRepository(private val webService: MealsWebService) {

    suspend fun getMealDetails(mealId: String): MealDetailsResponse? {
        val response = webService.api.getMealDetails(mealId)
        return if (response.isSuccessful) response.body() else null
    }
}
