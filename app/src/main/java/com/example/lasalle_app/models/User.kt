package com.example.lasalle_app.models

import androidx.compose.ui.graphics.vector.ImageVector
import java.time.LocalDate
import java.util.Date

data class User (
    val id : Int,
    val name : String,
    val lastName : String,
    val secondLastName : String,
    val birthDay : LocalDate,
    val email : String,
    val password : String,
    val img : String,
    val major : Major,
    val semester : Int,
    val payments : List<Payment>
)