import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavController

@Composable
fun MealDetailScreen(
    mealId: String,
    viewModel: MealDetailViewModel,
    navController: NavController
) {
    val mealDetail = viewModel.mealDetail.collectAsState()

    // Llamada para obtener detalles de la comida seleccionada
    LaunchedEffect(mealId) {
        viewModel.fetchMealDetails(mealId)
    }

    // Mostrar los detalles si están disponibles
    mealDetail.value?.let { meal ->
        Text(text = meal.name)
        Text(text = meal.instructions)
        Text(text = meal.thumbnail)
    }

    // Botón para regresar a la lista de comidas
    Button(onClick = { navController.popBackStack() }) {
        Text("Regresar a Lista de Comidas")
    }
}
