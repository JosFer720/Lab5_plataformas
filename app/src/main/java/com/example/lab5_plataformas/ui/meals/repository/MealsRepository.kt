package com.example.lab5_plataformas.ui.meals.repository

import com.example.lab5_plataformas.networking.MealsWebService
import com.example.lab5_plataformas.networking.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {

    fun getMeals(successCallback: (response: MealsCategoriesResponse?) -> Unit) {
        return webService.getMeals().enqueue(object : Callback<MealsCategoriesResponse> {

            override fun onResponse(
                call: Call<MealsCategoriesResponse>,
                response: Response<MealsCategoriesResponse>
            ) {
                if (response.isSuccessful)
                    successCallback(response.body())
            }

            override fun onFailure(call: Call<MealsCategoriesResponse>, t: Throwable) {
            }
        })
    }
}