import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.NavController

@Composable
fun MealsCategoriesScreen(
    viewModel: MealsCategoriesViewModel,
    navController: NavController
) {
    val mealsCategories = viewModel.mealsCategories.collectAsState()

    LazyColumn {
        items(mealsCategories.value) { category ->
            Text(
                text = category.name,
                modifier = androidx.compose.ui.Modifier.clickable {
                    navController.navigate("mealList/${category.name}")
                }
            )
        }
    }

    Button(onClick = { navController.navigate("mealList") }) {
        Text("Ver Comidas")
    }
}
