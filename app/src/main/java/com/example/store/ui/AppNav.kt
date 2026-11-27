package com.example.store.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

object Routes {
    const val HOME = "home"
    const val GAMES = "games"
    const val CATEGORIES = "categories"
    const val DOWNLOADS = "downloads"
    const val SETTINGS = "settings"
    const val APPS = "apps"
    const val DETAILS = "details/{packageName}"

    fun details(packageName: String) = "details/$packageName"
}

@Composable
fun AppNav(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME,
        modifier = modifier
    ) {
        composable(Routes.HOME) { PlaceholderScreen("الرئيسية") }
        composable(Routes.GAMES) { PlaceholderScreen("الألعاب") }
        composable(Routes.CATEGORIES) { PlaceholderScreen("الفئات") }
        composable(Routes.DOWNLOADS) { PlaceholderScreen("التنزيلات") }
        composable(Routes.SETTINGS) { PlaceholderScreen("الإعدادات") }
        composable(Routes.APPS) { PlaceholderScreen("التطبيقات") }
        composable(
            route = Routes.DETAILS,
            arguments = listOf(navArgument("packageName") { type = NavType.StringType })
        ) { entry ->
            val pkg = entry.arguments?.getString("packageName") ?: ""
            PlaceholderScreen("التفاصيل: $pkg")
        }
    }
}

@Composable
private fun PlaceholderScreen(title: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = title)
    }
}
