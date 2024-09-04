package com.example.lab5_plataformas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab5_plataformas.ui.theme.Lab5_plataformasTheme

data class Lugar(val titulo: String, val subtitulo: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListadoLugaresScreen(lugares: List<Lugar>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Venues") }
            )
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            items(lugares.size) { index ->
                LugarItem(lugar = lugares[index])
            }
        }
    }
}

@Composable
fun LugarItem(lugar: Lugar) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
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
                val lugares = listOf(
                    Lugar("Concert", "Place"),
                    Lugar("Concert", "Place"),
                    Lugar("Concert", "Place")
                )
                ListadoLugaresScreen(lugares)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListadoLugaresPreview() {
    Lab5_plataformasTheme {
        val lugares = listOf(
            Lugar("Concert", "Place"),
            Lugar("Concert", "Place"),
            Lugar("Concert", "Place")
        )
        ListadoLugaresScreen(lugares)
    }
}
