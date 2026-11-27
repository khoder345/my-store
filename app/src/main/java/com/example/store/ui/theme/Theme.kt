package com.example.store.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val StorePrimary = Color(0xFF0B57D0)
val StorePrimaryDark = Color(0xFF0842A0)
val StorePurple = Color(0xFF7C4DFF)
val StoreBg = Color(0xFFF6F7FC)
val StoreCard = Color(0xFFEDEFF8)
val StoreCardSelected = Color(0xFFDDE5F7)
val StoreChip = Color(0xFFE3E9F8)
val StoreText = Color(0xFF111827)
val StoreTextSecondary = Color(0xFF5B6475)
val StoreStar = Color(0xFFF2B01E)

val StoreBackgroundBrush = Brush.verticalGradient(
    listOf(Color(0xFFE8E6FA), Color(0xFFF6F7FC), Color(0xFFF6F7FC))
)

val StoreButtonBrush = Brush.horizontalGradient(
    listOf(Color(0xFF2F6BFF), Color(0xFF7C4DFF))
)

private val LightColors = lightColorScheme(
    primary = StorePrimary,
    onPrimary = Color.White,
    primaryContainer = StoreChip,
    onPrimaryContainer = StorePrimaryDark,
    secondary = StorePurple,
    background = StoreBg,
    onBackground = StoreText,
    surface = StoreBg,
    onSurface = StoreText,
    surfaceVariant = StoreCard,
    onSurfaceVariant = StoreTextSecondary,
    outline = Color(0xFFC5CBDD)
)

private val StoreShapes = Shapes(
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp)
)

@Composable
fun StoreTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        shapes = StoreShapes,
        content = content
    )
}
