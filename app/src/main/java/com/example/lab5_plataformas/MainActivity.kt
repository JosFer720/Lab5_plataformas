package com.example.lab5_plataformas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lab5_plataformas.ui.meals.view.MealsCategoriesScreen
import com.example.lab5_plataformas.ui.theme.Lab5_plataformasTheme

class MealsCategoriesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab5_plataformasTheme {
                MealsCategoriesScreen()
            }
        }
    }
}