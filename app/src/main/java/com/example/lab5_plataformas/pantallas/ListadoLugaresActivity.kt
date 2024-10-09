package com.example.lab5_plataformas.pantallas

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab5_plataformas.ui.theme.Lab5_plataformasTheme

@Composable
fun LugarItem(lugar: Lugar, onItemClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onItemClick(lugar.id) }, // Navegación al hacer clic
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = lugar.titulo,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = lugar.subtitulo,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

class ListadoLugaresActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab5_plataformasTheme {
                ListadoLugaresScreen(
                    lugares = GlobalData.lugares,
                    onItemClick = { lugarId ->
                        val intent = Intent(this, DetailsActivity::class.java)
                        intent.putExtra("LUGAR_ID", lugarId)
                        startActivity(intent)
                    },
                    onBackClick = { finish() } // Regresa a la pantalla anterior
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListadoLugaresScreen(lugares: List<Lugar>, onItemClick: (String) -> Unit, onBackClick: () -> Unit = {}) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Venues") },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
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
            items(lugares.size) { index ->
                LugarItem(lugar = lugares[index], onItemClick = onItemClick)
            }
        }
    }
}
