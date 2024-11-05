import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab5_plataformas.networking.response.MealResponse
import com.example.lab5_plataformas.ui.meals.repository.MealsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MealsCategoriesViewModel(private val repository: MealsRepository) : ViewModel() {

    private val _mealsCategories = MutableStateFlow<List<MealResponse>>(emptyList())
    val mealsCategories: StateFlow<List<MealResponse>> = _mealsCategories

    init {
        fetchMealsCategories()
    }

    private fun fetchMealsCategories() {
        viewModelScope.launch {
            _mealsCategories.value = repository.getMealsCategories()?.categories ?: emptyList()
        }
    }
}
