package com.example.lasalle_app.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.lasalle_app.utils.Screens

data class BottomNav(
    val title : String,
    val icon : ImageVector,
    val route : String
){
    companion object{
        val items = listOf(
            BottomNav(
                title = "Inicio",
                icon = Icons.Default.Home,
                route = Screens.Home.route
            ),
            BottomNav(
                title = "Calificaciones",
                icon = Icons.Default.Menu,
                route = Screens.Grades.route
            ),
            BottomNav(
                title = "Calendario",
                icon = Icons.Default.DateRange,
                route = Screens.Calendar.route
            ),
            BottomNav(
                title = "Configuracion",
                icon = Icons.Default.Settings,
                route = Screens.Settings.route
            )
        )
    }
}