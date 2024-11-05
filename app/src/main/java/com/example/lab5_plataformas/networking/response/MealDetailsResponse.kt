package com.example.lab5_plataformas.networking.response

import com.google.gson.annotations.SerializedName

data class MealDetailsResponse(
    @SerializedName("meals") val meals: List<MealDetail>
)

data class MealDetail(
    @SerializedName("idMeal") val id: String,
    @SerializedName("strMeal") val name: String,
    @SerializedName("strInstructions") val instructions: String,
    @SerializedName("strMealThumb") val thumbnail: String
)
