package com.example.lab5_plataformas.ui.meals.repository

import com.example.lab5_plataformas.networking.MealsWebService
import com.example.lab5_plataformas.networking.response.MealsCategoriesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {

    // Método suspendido para obtener las categorías de comidas
    suspend fun getMeals(): MealsCategoriesResponse? {
        return withContext(Dispatchers.IO) {
            val response = webService.getMeals()
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        }
    }
}