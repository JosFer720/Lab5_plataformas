import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.NavController

@Composable
fun MealListScreen(
    category: String,
    viewModel: MealListViewModel,
    navController: NavController
) {
    val meals = viewModel.meals.collectAsState(initial = emptyList())

    // Llamada para obtener comidas de la categoría seleccionada
    LaunchedEffect(category) {
        viewModel.fetchMealsByCategory(category)
    }

    LazyColumn {
        items(meals.value ?: emptyList()) { meal ->
            Text(
                text = meal.name,
                modifier = androidx.compose.ui.Modifier.clickable {
                    navController.navigate("mealDetail/${meal.id}")
                }
            )
        }
    }

    // Botón para regresar a la pantalla de categorías
    Button(onClick = { navController.navigate("mealsCategories") }) {
        Text("Regresar a Categorías")
    }
}
