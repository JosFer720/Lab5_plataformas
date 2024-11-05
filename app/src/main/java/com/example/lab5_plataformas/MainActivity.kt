package com.example.lab5_plataformas

import MealDetailScreen
import MealDetailViewModel
import MealListScreen
import MealListViewModel
import MealsCategoriesScreen
import MealsCategoriesViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lab5_plataformas.ui.theme.Lab5_plataformasTheme
import com.example.lab5_plataformas.pantallas.ui.meals.repository.MealDetailRepository
import com.example.lab5_plataformas.pantallas.ui.meals.repository.MealListRepository
import com.example.lab5_plataformas.ui.meals.repository.MealsRepository
import com.example.lab5_plataformas.networking.MealsWebService

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar MealsWebService
        val webService = MealsWebService()

        // Crear instancias de los Repositories
        val mealsRepository = MealsRepository(webService)
        val mealListRepository = MealListRepository(webService)
        val mealDetailRepository = MealDetailRepository(webService)

        // Crear instancias de los ViewModels manualmente
        val mealsCategoriesViewModel = MealsCategoriesViewModel(mealsRepository)
        val mealListViewModel = MealListViewModel(mealListRepository)
        val mealDetailViewModel = MealDetailViewModel(mealDetailRepository)

        setContent {
            Lab5_plataformasTheme {
                AppNavigation(
                    categoriesViewModel = mealsCategoriesViewModel,
                    mealListViewModel = mealListViewModel,
                    mealDetailViewModel = mealDetailViewModel
                )
            }
        }
    }
}

@Composable
fun AppNavigation(
    categoriesViewModel: MealsCategoriesViewModel,
    mealListViewModel: MealListViewModel,
    mealDetailViewModel: MealDetailViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "mealsCategories") {

        // Pantalla de categorías
        composable("mealsCategories") {
            MealsCategoriesScreen(
                viewModel = categoriesViewModel,
                navController = navController
            )
        }

        // Pantalla de lista de comidas por categoría
        composable(
            "mealList/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: ""
            MealListScreen(
                category = category,
                viewModel = mealListViewModel,
                navController = navController
            )
        }

        // Pantalla de detalles de comida
        composable(
            "mealDetail/{mealId}",
            arguments = listOf(navArgument("mealId") { type = NavType.StringType })
        ) { backStackEntry ->
            val mealId = backStackEntry.arguments?.getString("mealId") ?: ""
            MealDetailScreen(
                mealId = mealId,
                viewModel = mealDetailViewModel,
                navController = navController
            )
        }
    }
}
