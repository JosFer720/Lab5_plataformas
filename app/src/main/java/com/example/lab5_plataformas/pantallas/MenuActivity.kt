package com.example.lab5_plataformas.pantallas

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lab5_plataformas.ui.theme.Lab5_plataformasTheme

class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab5_plataformasTheme {
                MenuScreen(
                    navigateToProfile = { navigateToProfile() },
                    navigateToEventos = { navigateToListadoLugares() }
                )
            }
        }
    }

    private fun navigateToProfile() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToListadoLugares() {
        val intent = Intent(this, ListadoLugaresActivity::class.java)
        startActivity(intent)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navigateToProfile: () -> Unit = {}, navigateToEventos: () -> Unit = {}) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Events") },
                actions = {
                    TextButton(onClick = { navigateToProfile() }) {
                        Text("Perfil", color = Color.Black)
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            item {
                Text(
                    text = "Your favorites",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            if (GlobalData.favoriteEvents.isEmpty()) {
                item {
                    Text("No hay eventos favoritos", style = MaterialTheme.typography.bodyMedium)
                }
            } else {
                // Mostrar los objetos completos de los lugares favoritos
                items(GlobalData.favoriteEvents.size) { index ->
                    val lugar = GlobalData.favoriteEvents[index]
                    LugarItem(lugar = lugar, onItemClick = { /* Acción si se hace clic en el lugar */ })
                }
            }

            item {
                Button(onClick = { navigateToEventos() }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Ver más eventos")
                }
            }
        }
    }
}