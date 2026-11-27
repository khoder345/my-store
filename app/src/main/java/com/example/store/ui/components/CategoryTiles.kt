package com.example.store.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store.ui.theme.StoreText

data class CategoryTile(
    val id: String,
    val label: String,
    val icon: ImageVector,
    val background: Color,
    val tint: Color
)

// الترتيب متل الصورة: من اليسار لليمين
val homeCategoryTiles = listOf(
    CategoryTile("games", "Games", Icons.Filled.SportsEsports, Color(0xFFE6E4FA), Color(0xFF3F5BD9)),
    CategoryTile("apps", "Apps", Icons.Filled.Apps, Color(0xFFDDE8FA), Color(0xFF2F6BFF)),
    CategoryTile("personalization", "Personalization", Icons.Filled.Palette, Color(0xFFECE9EE), Color(0xFFF08A24)),
    CategoryTile("multimedia", "Multimedia", Icons.Filled.PlayArrow, Color(0xFFF0E4F7), Color(0xFF7C4DFF))
)

@Composable
fun CategoryTilesSection(
    modifier: Modifier = Modifier,
    tiles: List<CategoryTile> = homeCategoryTiles,
    onSeeAll: () -> Unit = {},
    onTileClick: (CategoryTile) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        SectionHeader(title = "أفضل الألعاب", onSeeAll = onSeeAll)
        Spacer(Modifier.height(10.dp))

        // نثبّت الاتجاه من اليسار حتى يطلع الترتيب متل الصورة
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                tiles.forEach { tile ->
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable { onTileClick(tile) },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(54.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(tile.background),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = tile.icon,
                                contentDescription = tile.label,
                                tint = tile.tint,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = tile.label,
                            fontSize = 10.sp,
                            color = StoreText,
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}
