package com.example.myapplication.ui.mealdetail.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.networking.response.Mealslookup
import com.example.myapplication.ui.mealdetail.viewmodel.MealsdetailViewModel

@Composable
fun MealDetailScreen(navController: NavController, viewModel: MealsdetailViewModel = viewModel()) {
    var meals by remember { mutableStateOf(emptyList<Mealslookup>()) }

    viewModel.getMealslookup { response ->
        response?.meals?.let {
            meals = it
        }
    }

    if (meals.isNotEmpty()) {
        MealList(meals = meals)
    } else {
        Text(
            text = "Cargando detalles de la comida...",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .padding(16.dp)
        )
    }
}

@Composable
fun MealList(meals: List<Mealslookup>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(meals) { meal ->
            MealCard(meal = meal)
        }
    }
}

@Composable
fun MealCard(meal: Mealslookup) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFFF1F8E9), Color(0xFFA5D6A7))
                    )
                )
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = meal.strMealThumb),
                contentDescription = meal.strMeal,
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = meal.strMeal,
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFF2E7D32),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Categoría: ${meal.strCategory}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF388E3C),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                text = "Área: ${meal.strArea}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF388E3C),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Instrucciones:",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF1B5E20),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                text = meal.strInstructions,
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF4CAF50),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify
            )
        }
    }
}
