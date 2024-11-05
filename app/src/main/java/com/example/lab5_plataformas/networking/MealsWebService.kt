package com.example.lab5_plataformas.networking

import com.example.lab5_plataformas.networking.api.MealsApi
import com.example.lab5_plataformas.networking.response.MealsByCategoryResponse
import com.example.lab5_plataformas.networking.response.MealsCategoriesResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealsWebService {

    val api: MealsApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(MealsApi::class.java)
    }

    suspend fun getMeals(): Response<MealsCategoriesResponse> {
        return api.getMeals()
    }

    suspend fun getMealsByCategory(category: String): Response<MealsByCategoryResponse> {
        return api.getMealsByCategory(category)
    }
}
