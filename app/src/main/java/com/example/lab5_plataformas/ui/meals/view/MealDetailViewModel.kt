package com.example.lab5_plataformas.pantallas.ui.meals.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab5_plataformas.pantallas.ui.meals.repository.MealDetailRepository
import com.example.lab5_plataformas.networking.response.MealDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MealDetailViewModel(private val repository: MealDetailRepository) : ViewModel() {

    private val _mealDetail = MutableStateFlow<MealDetail?>(null)
    val mealDetail: StateFlow<MealDetail?> get() = _mealDetail

    fun fetchMealDetails(mealId: String) {
        viewModelScope.launch {
            val response = repository.getMealDetails(mealId)
            if (response.isSuccessful) {
                _mealDetail.value = response.body()?.meals?.firstOrNull()
            }
        }
    }
}