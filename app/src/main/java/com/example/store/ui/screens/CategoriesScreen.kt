package com.example.store.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store.ui.theme.StoreBackgroundBrush
import com.example.store.ui.theme.StoreCard
import com.example.store.ui.theme.StoreText
import com.example.store.ui.theme.StoreTextSecondary

data class CategoryItem(
    val id: String,
    val name: String,
    val count: Int,
    val icon: ImageVector,
    val tint: Color
)

val sampleCategories = listOf(
    CategoryItem("photo", "التصوير والفيديو", 98, Icons.Filled.CameraAlt, Color(0xFF3B3B4F)),
    CategoryItem("social", "التواصل الاجتماعي", 153, Icons.Filled.Group, Color(0xFF2F6BFF)),
    CategoryItem("education", "التعليم", 75, Icons.Filled.School, Color(0xFF1B4F72)),
    CategoryItem("tools", "الأدوات", 210, Icons.Filled.Build, Color(0xFF3FA796)),
    CategoryItem("health", "الصحة واللياقة", 60, Icons.Filled.Favorite, Color(0xFFE5484D)),
    CategoryItem("entertainment", "الترفيه", 112, Icons.Filled.SportsEsports, Color(0xFF7C4DFF)),
    CategoryItem("travel", "السفر", 55, Icons.Filled.Flight, Color(0xFF2F9BFF)),
    CategoryItem("shopping", "التسوق", 95, Icons.Filled.ShoppingBag, Color(0xFFF2557A))
)

@Composable
fun CategoriesScreen(
    modifier: Modifier = Modifier,
    onCategoryClick: (CategoryItem) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(StoreBackgroundBrush)
            .statusBarsPadding()
    ) {
        Text(
            text = "الفئات",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = StoreText,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(StoreCard)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null,
                tint = StoreTextSecondary,
                modifier = Modifier.size(22.dp)
            )
            Spacer(Modifier.size(8.dp))
            Text(
                text = "ابحث عن ألعاب...",
                fontSize = 14.sp,
                color = StoreTextSecondary
            )
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "تصفح الفئات",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = StoreText,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(Modifier.height(10.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(
                start = 16.dp, end = 16.dp, bottom = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(sampleCategories) { cat ->
                CategoryCard(cat = cat, onClick = { onCategoryClick(cat) })
            }
        }
    }
}

@Composable
private fun CategoryCard(
    cat: CategoryItem,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .shadow(3.dp, RoundedCornerShape(20.dp), ambientColor = Color(0x22000000))
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White.copy(alpha = 0.85f))
            .clickable { onClick() }
            .padding(14.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(cat.tint.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = cat.icon,
                contentDescription = cat.name,
                tint = cat.tint,
                modifier = Modifier.size(30.dp)
            )
        }
        Column {
            Text(
                text = cat.name,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = StoreText,
                maxLines = 1
            )
            Text(
                text = cat.count.toString(),
                fontSize = 13.sp,
                color = StoreTextSecondary
            )
        }
    }
}
