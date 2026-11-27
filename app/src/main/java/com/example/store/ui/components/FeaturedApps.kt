package com.example.store.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store.ui.theme.StorePrimary
import com.example.store.ui.theme.StoreStar
import com.example.store.ui.theme.StoreText
import com.example.store.ui.theme.StoreTextSecondary

data class FeaturedApp(
    val name: String,
    val rating: String,
    val size: String,
    val color: Color
)

val sampleFeaturedApps = listOf(
    FeaturedApp("إنستغرام", "4.3", "74 MB", Color(0xFFE1306C)),
    FeaturedApp("فيسبوك", "4.3", "65 MB", Color(0xFF1877F2)),
    FeaturedApp("تيك توك", "4.3", "109 MB", Color(0xFF111111)),
    FeaturedApp("واتساب", "4.3", "90 MB", Color(0xFF25D366)),
    FeaturedApp("تيليجرام", "4.3", "70 MB", Color(0xFF229ED9)),
    FeaturedApp("سناب شات", "4.3", "120 MB", Color(0xFFF2B01E))
)

@Composable
fun SectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    onSeeAll: () -> Unit = {}
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = StoreText
        )
        Spacer(Modifier.weight(1f))
        Row(
            modifier = Modifier.clickable { onSeeAll() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "مشاهدة الكل",
                fontSize = 13.sp,
                color = StorePrimary
            )
            Spacer(Modifier.width(4.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = StorePrimary,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun FeaturedAppsSection(
    modifier: Modifier = Modifier,
    apps: List<FeaturedApp> = sampleFeaturedApps,
    onSeeAll: () -> Unit = {},
    onDownload: (FeaturedApp) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        SectionHeader(title = "التطبيقات المميزة", onSeeAll = onSeeAll)
        Spacer(Modifier.height(10.dp))
        apps.forEach { app ->
            FeaturedAppRow(app = app, onDownload = onDownload)
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
private fun FeaturedAppRow(
    app: FeaturedApp,
    onDownload: (FeaturedApp) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(app.color),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = app.name.first().toString(),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.width(12.dp))

            Column {
                Text(
                    text = app.name,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = StoreText
                )
                Spacer(Modifier.height(2.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = StoreStar,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(Modifier.width(3.dp))
                    Text(
                        text = "${app.rating} • ${app.size}",
                        fontSize = 12.sp,
                        color = StoreTextSecondary
                    )
                }
            }

            Spacer(Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(StorePrimary)
                    .clickable { onDownload(app) }
                    .padding(horizontal = 18.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "تنزيل",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
