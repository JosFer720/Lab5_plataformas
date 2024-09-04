package com.example.lab5_plataformas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.lab5_plataformas.ui.theme.Lab5_plataformasTheme

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab5_plataformasTheme {
                ProfileScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://images.unsplash.com/photo-1506748686214-e9df14d4d9d0"), // URL de la imagen de fondo
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Imagen de avatar circular
                Image(
                    painter = rememberAsyncImagePainter("https://images.unsplash.com/photo-1603415526960-f6e0d0653662"), // URL de la imagen de perfil
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center)
                        .padding(top = 50.dp)
                )
            }

            // Nombre del usuario
            Text(
                text = "User",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 16.dp)
            )

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                ProfileOption(iconUrl = "https://img.icons8.com/ios-glyphs/30/000000/edit.png", title = "Edit Profile")
                ProfileOption(iconUrl = "https://img.icons8.com/ios-glyphs/30/000000/lock.png", title = "Reset Password")
                ProfileOption(iconUrl = "https://img.icons8.com/ios-glyphs/30/000000/bell.png", title = "Notifications", switchOption = true)
                ProfileOption(iconUrl = "https://img.icons8.com/ios-glyphs/30/000000/star.png", title = "Favorites")
            }
        }
    }
}

@Composable
fun ProfileOption(iconUrl: String, title: String, switchOption: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(iconUrl),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.weight(1f).padding(start = 16.dp)
        )
        if (switchOption) {
            Switch(
                checked = false,
                onCheckedChange = {}
            )
        } else {
            Image(
                painter = rememberAsyncImagePainter("https://img.icons8.com/ios-glyphs/30/000000/chevron-right.png"),
                contentDescription = null
            )
        }
    }
}
