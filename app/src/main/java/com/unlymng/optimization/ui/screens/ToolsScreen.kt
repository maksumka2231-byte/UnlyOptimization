package com.unlymng.optimization.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.unlymng.optimization.ui.theme.NeonCyan
import com.unlymng.optimization.ui.theme.SurfaceDark
import com.unlymng.optimization.ui.viewmodel.ToolsViewModel

/**
 * Экран Tools - различные инструменты оптимизации и управления
 */
@Composable
fun ToolsScreen(
    viewModel: ToolsViewModel = hiltViewModel()
) {
    val gameMode by viewModel.gameMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "ИНСТРУМЕНТЫ",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = NeonCyan,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Game Mode Toggle
        ToolCard(title = "Режим игры") {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Включить", color = Color.White, fontWeight = FontWeight.SemiBold)
                Switch(
                    checked = gameMode,
                    onCheckedChange = { viewModel.toggleGameMode(it) }
                )
            }
        }

        // RAM Cleaner
        ToolCard(title = "Очистка RAM") {
            Button(
                onClick = { viewModel.cleanRAM() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = NeonCyan
                )
            ) {
                Text("Очистить", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }

        // App Manager
        ToolCard(title = "Менеджер приложений") {
            Button(
                onClick = { viewModel.openAppManager() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = NeonCyan
                )
            ) {
                Text("Открыть", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }

        // Ping Test
        ToolCard(title = "Тест пинга") {
            Button(
                onClick = { viewModel.testPing() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = NeonCyan
                )
            ) {
                Text("Проверить", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }

        // Network Optimizer
        ToolCard(title = "Оптимизация сети") {
            Button(
                onClick = { viewModel.optimizeNetwork() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = NeonCyan
                )
            ) {
                Text("Оптимизировать", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ToolCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = SurfaceDark
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = NeonCyan,
                modifier = Modifier.padding(16.dp)
            )
            content()
        }
    }
}
