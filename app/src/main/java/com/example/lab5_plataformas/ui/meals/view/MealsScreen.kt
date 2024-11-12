package com.example.myapplication.ui.meals.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.myapplication.navegation.AppScreens
import com.example.myapplication.networking.response.MealsFilter
import com.example.myapplication.ui.meals.viewmodel.MealsViewModel

@Composable
fun MealsScreen(navController: NavController, viewModel: MealsViewModel = viewModel()) {
    var meals by remember { mutableStateOf(emptyList<MealsFilter>()) }

    viewModel.getMealsFilter { response ->
        response?.meals?.let {
            meals = it
        }
    }
    FilterList(meals = meals, navController = navController)
}

@Composable
fun FilterList(meals: List<MealsFilter>, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(meals) { meal ->
            FilterCard(meal = meal, onClick = {
                navController.navigate(AppScreens.MealDetailScreen.route)
            })
        }
    }
}

@Composable
fun FilterCard(meal: MealsFilter, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFFBBDEFB), Color(0xFF90CAF9))
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
                color = Color(0xFF0D47A1),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
