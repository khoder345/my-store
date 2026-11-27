package com.example.store

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.store.ui.AppNav
import com.example.store.ui.components.StoreBottomBar
import com.example.store.ui.components.StoreTab
import com.example.store.ui.theme.StoreBg
import com.example.store.ui.theme.StoreTheme

class MainActivity : ComponentActivity() {

    private val vm: StoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                StoreTheme {
                    val navController = rememberNavController()
                    val backStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = backStackEntry?.destination?.route
                    val showBottomBar = StoreTab.entries.any { it.route == currentRoute }

                    Scaffold(
                        containerColor = StoreBg,
                        bottomBar = {
                            if (showBottomBar) {
                                StoreBottomBar(
                                    currentRoute = currentRoute,
                                    onSelect = { tab ->
                                        navController.navigate(tab.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    ) { padding ->
                        AppNav(
                            navController = navController,
                            modifier = Modifier.padding(padding)
                        )
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        vm.refreshInstallStates()
    }
}
