package com.unlymng.optimization.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.unlymng.optimization.ui.screens.BoostScreen
import com.unlymng.optimization.ui.screens.MonitorScreen
import com.unlymng.optimization.ui.screens.ToolsScreen
import com.unlymng.optimization.ui.screens.SettingsScreen

/**
 * Главная навигация приложения с 4 основными экранами
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = "boost"
            ) {
                composable("boost") { BoostScreen() }
                composable("monitor") { MonitorScreen() }
                composable("tools") { ToolsScreen() }
                composable("settings") { SettingsScreen() }
            }
        }
    }
}
