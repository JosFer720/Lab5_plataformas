package com.example.lab5_plataformas.pantallas.ui.meals.repository

import com.example.lab5_plataformas.networking.MealsWebService
import com.example.lab5_plataformas.networking.api.MealsApi
import com.example.lab5_plataformas.networking.response.MealsByCategoryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MealListRepository(private val webService: MealsWebService) {

    suspend fun getMealsByCategory(category: String): MealsByCategoryResponse? {
        val response = webService.api.getMealsByCategory(category)
        return if (response.isSuccessful) response.body() else null
    }
}
