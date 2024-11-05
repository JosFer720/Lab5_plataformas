package com.example.lab5_plataformas.pantallas.ui.meals.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab5_plataformas.pantallas.ui.meals.repository.MealListRepository
import com.example.lab5_plataformas.networking.response.MealResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MealListViewModel(private val repository: MealListRepository) : ViewModel() {

    private val _meals = MutableStateFlow<List<MealResponse>?>(null)
    val meals: StateFlow<List<MealResponse>?> get() = _meals

    fun fetchMealsByCategory(category: String) {
        viewModelScope.launch {
            val response = repository.getMealsByCategory(category)
            if (response.isSuccessful) {
                _meals.value = response.body()?.meals
            }
        }
    }
}
