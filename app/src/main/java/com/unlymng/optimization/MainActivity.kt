package com.unlymng.optimization

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.unlymng.optimization.ui.theme.UnlyOptimizationTheme
import com.unlymng.optimization.ui.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint

/**
 * Главная Activity приложения Unly Optimization
 * Содержит основной Compose UI и навигацию
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnlyOptimizationTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black),
                    color = Color.Black
                ) {
                    AppNavigation()
                }
            }
        }
    }
}
