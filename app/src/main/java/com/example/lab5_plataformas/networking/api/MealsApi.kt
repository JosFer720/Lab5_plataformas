package com.example.lab5_plataformas.networking.api

import com.example.lab5_plataformas.networking.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.http.GET

interface MealsApi {

    @GET("categories.php")
    fun getMeals(): Call<MealsCategoriesResponse>
}