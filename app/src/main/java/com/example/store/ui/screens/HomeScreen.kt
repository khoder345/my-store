package com.example.store.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.store.ui.components.CategoryTilesSection
import com.example.store.ui.components.FeaturedAppsSection
import com.example.store.ui.components.FeaturedGamesSection
import com.example.store.ui.components.HomeBanner
import com.example.store.ui.components.HomeHeader
import com.example.store.ui.components.HomeSearchBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onOpenApps: () -> Unit = {},
    onOpenGames: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        HomeHeader()
        HomeSearchBar()
        Spacer(Modifier.height(12.dp))
        HomeBanner(onDiscover = onOpenApps)
        Spacer(Modifier.height(16.dp))
        FeaturedAppsSection(onSeeAll = onOpenApps)
        Spacer(Modifier.height(16.dp))
        CategoryTilesSection(
            onSeeAll = onOpenGames,
            onTileClick = { tile ->
                if (tile.id == "apps") onOpenApps() else onOpenGames()
            }
        )
        Spacer(Modifier.height(16.dp))
        FeaturedGamesSection(onSeeAll = onOpenGames)
        Spacer(Modifier.height(16.dp))
    }
}
