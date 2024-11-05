package com.example.lab5_plataformas.ui.meals.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab5_plataformas.networking.response.MealResponse
import com.example.lab5_plataformas.ui.meals.repository.MealsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MealsCategoriesViewModel(private val repository: MealsRepository = MealsRepository()) : ViewModel() {

    // Exponer una lista de MealResponse como StateFlow
    private val _mealsCategories = MutableStateFlow<List<MealResponse>>(emptyList())
    val mealsCategories: StateFlow<List<MealResponse>> get() = _mealsCategories

    init {
        fetchMeals()
    }

    private fun fetchMeals() {
        viewModelScope.launch {
            val response = repository.getMeals()
            _mealsCategories.value = response?.categories ?: emptyList()
        }
    }
}
