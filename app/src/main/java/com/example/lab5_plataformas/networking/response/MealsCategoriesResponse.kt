package com.example.lab5_plataformas.networking.response

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

data class MealsCategoriesResponse(val categories: List<MealResponse>)