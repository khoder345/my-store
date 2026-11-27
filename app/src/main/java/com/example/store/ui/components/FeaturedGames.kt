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

data class FeaturedGame(
    val name: String,
    val rating: String,
    val size: String,
    val color: Color
)

val sampleFeaturedGames = listOf(
    FeaturedGame("ببجي موبايل", "4.8", "1.1 GB", Color(0xFFF2B01E)),
    FeaturedGame("جينشين إمباكت", "4.7", "1.9 GB", Color(0xFF7C4DFF)),
    FeaturedGame("إي فوتبول", "4.4", "1.5 GB", Color(0xFF1B4FD8)),
    FeaturedGame("كول أوف ديوتي", "4.6", "1.3 GB", Color(0xFF3B3B3B)),
    FeaturedGame("ماينكرافت", "4.5", "200 MB", Color(0xFF5B8C3A)),
    FeaturedGame("فري فاير", "4.5", "1.2 GB", Color(0xFFF08A24))
)

@Composable
fun FeaturedGamesSection(
    modifier: Modifier = Modifier,
    games: List<FeaturedGame> = sampleFeaturedGames,
    onSeeAll: () -> Unit = {},
    onInstall: (FeaturedGame) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        SectionHeader(title = "الألعاب المميزة", onSeeAll = onSeeAll)
        Spacer(Modifier.height(10.dp))
        games.forEach { game ->
            FeaturedGameRow(game = game, onInstall = onInstall)
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
private fun FeaturedGameRow(
    game: FeaturedGame,
    onInstall: (FeaturedGame) -> Unit
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
                    .background(game.color),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = game.name.first().toString(),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.width(12.dp))

            Column {
                Text(
                    text = game.name,
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
                        text = "${game.rating} • ${game.size}",
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
                    .clickable { onInstall(game) }
                    .padding(horizontal = 18.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "تثبيت",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
