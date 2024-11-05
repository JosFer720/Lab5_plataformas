package com.example.lab5_plataformas.networking.api

import com.example.lab5_plataformas.networking.response.MealDetailsResponse
import com.example.lab5_plataformas.networking.response.MealsByCategoryResponse
import com.example.lab5_plataformas.networking.response.MealsCategoriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApi {

    @GET("categories.php")
    suspend fun getMeals(): Response<MealsCategoriesResponse>

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): Response<MealsByCategoryResponse>

    @GET("lookup.php")
    suspend fun getMealDetails(@Query("i") mealId: String): Response<MealDetailsResponse>
}
