package com.example.lab5_plataformas.ui.meals.repository

import com.example.lab5_plataformas.networking.MealsWebService
import com.example.lab5_plataformas.networking.api.MealsApi
import com.example.lab5_plataformas.networking.response.MealsCategoriesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealsRepository(private val webService: MealsWebService) {

    suspend fun getMealsCategories(): MealsCategoriesResponse? {
        val response = webService.api.getMealsCategories()
        return if (response.isSuccessful) response.body() else null
    }
}
