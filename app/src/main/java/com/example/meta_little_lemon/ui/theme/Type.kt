package com.example.meta_little_lemon.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    headlineSmall = TextStyle(
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        color = charcoal
    ),
    headlineMedium = TextStyle(
        color = charcoal,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    ),
    bodySmall = TextStyle(
        color = green
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        color =green
    )
)