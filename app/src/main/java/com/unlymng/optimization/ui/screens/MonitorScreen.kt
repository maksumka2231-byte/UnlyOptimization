package com.unlymng.optimization.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.unlymng.optimization.ui.viewmodel.MonitorViewModel

/**
 * Экран Monitor - мониторинг производительности и системных параметров
 */
@Composable
fun MonitorScreen(
    viewModel: MonitorViewModel = hiltViewModel()
) {
    val cpuUsage by viewModel.cpuUsage.collectAsState()
    val ramUsage by viewModel.ramUsage.collectAsState()
    val temperature by viewModel.temperature.collectAsState()
    val fps by viewModel.fps.collectAsState()
    val ping by viewModel.ping.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "МОНИТОРИНГ",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = NeonCyan,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // CPU Usage
        MonitorCard(
            title = "CPU",
            value = "$cpuUsage%",
            subtext = "Использование процессора"
        )

        // RAM Usage
        MonitorCard(
            title = "RAM",
            value = "$ramUsage%",
            subtext = "Оперативная память"
        )

        // Temperature
        MonitorCard(
            title = "ТЕМПЕРАТУРА",
            value = "${temperature}°C",
            subtext = "Батарея и процессор"
        )

        // FPS
        MonitorCard(
            title = "FPS",
            value = "$fps fps",
            subtext = "Кадры в секунду"
        )

        // PING
        MonitorCard(
            title = "PING",
            value = "${ping}ms",
            subtext = "Задержка сети"
        )
    }
}

@Composable
fun MonitorCard(
    title: String,
    value: String,
    subtext: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = SurfaceDark
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = NeonCyan
                )
                Text(
                    text = subtext,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            Text(
                text = value,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
