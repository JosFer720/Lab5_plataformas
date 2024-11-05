package com.example.lab5_plataformas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lab5_plataformas.pantallas.ui.meals.view.MealDetailScreen
import com.example.lab5_plataformas.ui.meals.view.MealsCategoriesScreen
import com.example.lab5_plataformas.ui.meals.view.MealsCategoriesViewModel
import com.example.lab5_plataformas.ui.theme.Lab5_plataformasTheme

class MainActivity : ComponentActivity() {

    private val mealsCategoriesViewModel: MealsCategoriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab5_plataformasTheme {
                AppNavigation(mealsCategoriesViewModel)
            }
        }
    }
}

@Composable
fun AppNavigation(viewModel: MealsCategoriesViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "mealsCategories") {
        // Pantalla de categorías de comidas
        composable("mealsCategories") {
            MealsCategoriesScreen(
                viewModel = viewModel,
                onCategorySelected = { categoryId ->
                    // Navega a los detalles de la comida seleccionada
                    navController.navigate("mealDetail/$categoryId")
                }
            )
        }

        // Pantalla de detalles de comida
        composable(
            "mealDetail/{mealId}",
            arguments = listOf(navArgument("mealId") { type = NavType.StringType })
        ) { backStackEntry ->
            val mealId = backStackEntry.arguments?.getString("mealId") ?: ""
            MealDetailScreen(mealId = mealId)
        }
    }
}
