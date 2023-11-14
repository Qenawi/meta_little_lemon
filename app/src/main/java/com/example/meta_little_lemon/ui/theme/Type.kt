package com.example.meta_little_lemon.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.meta_little_lemon.R

// Set of Material typography styles to start with

val reg = FontFamily(Font(R.font.font_regular))
val mid = FontFamily(Font(R.font.font_medium))
val semi = FontFamily(Font(R.font.font_semibold))
val Typography = Typography(
    headlineSmall = TextStyle(
        fontSize = 26.sp,
        fontFamily = mid,
        fontWeight = FontWeight.Bold,
        color = black
    ),
    headlineMedium = TextStyle(
        color = black,
        fontFamily = semi,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    ),
    bodySmall = TextStyle(
        fontFamily = reg,
        color = black
    ),
    bodyMedium = TextStyle(
        fontFamily = semi,
        color = black
    )
)