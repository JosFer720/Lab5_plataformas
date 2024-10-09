package com.example.lab5_plataformas.pantallas

import androidx.compose.runtime.mutableStateListOf

object GlobalData {
    // Lista de lugares (ejemplo)
    val lugares = listOf(
        Lugar(id = "1", titulo = "Museo de Historia", subtitulo = "Visita cultural"),
        Lugar(id = "2", titulo = "Parque Central", subtitulo = "Diversión al aire libre"),
        Lugar(id = "3", titulo = "Teatro Nacional", subtitulo = "Espectáculo en vivo")
    )

    // Lista observable de favoritos (ahora almacena objetos Lugar)
    val favoriteEvents = mutableStateListOf<Lugar>()

    // Función para verificar si un lugar es favorito
    fun isFavorite(lugar: Lugar): Boolean {
        return favoriteEvents.any { it.id == lugar.id }
    }

    // Función para agregar o quitar de favoritos
    fun toggleFavorite(lugar: Lugar) {
        if (isFavorite(lugar)) {
            favoriteEvents.removeIf { it.id == lugar.id }
        } else {
            favoriteEvents.add(lugar)
        }
    }
}
