package com.example.lab5_plataformas.networking.response

import com.google.gson.annotations.SerializedName

data class MealsByCategoryResponse(
    @SerializedName("meals") val meals: List<MealResponse>
)
