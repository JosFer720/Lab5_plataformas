package com.example.myapplication.ui.categories.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
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
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.networking.response.MealsCatergories
import com.example.myapplication.ui.categories.viewmodel.CategoriesViewModel
import androidx.navigation.NavController
import com.example.myapplication.navegation.AppScreens

@Composable
fun CategoriesScreen(navController: NavController, viewModel: CategoriesViewModel = viewModel()) {
    var categories by remember { mutableStateOf(emptyList<MealsCatergories>()) }

    viewModel.getMealsCategories { response ->
        response?.categories?.let {
            categories = it
        }
    }

    CategoryList(categories = categories, navController = navController)
}

@Composable
fun CategoryList(categories: List<MealsCatergories>, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(categories) { category ->
            CategoryCard(category = category, onClick = {
                navController.navigate(AppScreens.MealsScreen.route)
            })
        }
    }
}

@Composable
fun CategoryCard(category: MealsCatergories, onClick: () -> Unit) {
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
                        colors = listOf(Color(0xFFB2DFDB), Color(0xFFE0F7FA))
                    )
                )
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = category.strCategoryThumb),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = category.strCategory,
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFF004D40),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = category.strCategoryDescription,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF00796B),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify
            )
        }
    }
}
