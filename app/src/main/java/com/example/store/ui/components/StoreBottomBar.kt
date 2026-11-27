package com.example.store.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.SportsEsports
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store.ui.theme.StorePrimary
import com.example.store.ui.theme.StoreTextSecondary

enum class StoreTab(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector
) {
    HOME("home", "الرئيسية", Icons.Outlined.Home, Icons.Filled.Home),
    GAMES("games", "الألعاب", Icons.Outlined.SportsEsports, Icons.Filled.SportsEsports),
    CATEGORIES("categories", "الفئات", Icons.Outlined.GridView, Icons.Filled.GridView),
    DOWNLOADS("downloads", "التنزيلات", Icons.Outlined.Download, Icons.Filled.Download),
    SETTINGS("settings", "الإعدادات", Icons.Outlined.Settings, Icons.Filled.Settings)
}

@Composable
fun StoreBottomBar(
    currentRoute: String?,
    onSelect: (StoreTab) -> Unit
) {
    NavigationBar(
        containerColor = Color(0xFFF6F7FC),
        tonalElevation = 0.dp
    ) {
        StoreTab.entries.forEach { tab ->
            val selected = currentRoute == tab.route
            NavigationBarItem(
                selected = selected,
                onClick = { onSelect(tab) },
                icon = {
                    Icon(
                        imageVector = if (selected) tab.selectedIcon else tab.icon,
                        contentDescription = tab.label
                    )
                },
                label = {
                    Text(
                        text = tab.label,
                        fontSize = 11.sp,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = StorePrimary,
                    selectedTextColor = StorePrimary,
                    unselectedIconColor = StoreTextSecondary,
                    unselectedTextColor = StoreTextSecondary,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
