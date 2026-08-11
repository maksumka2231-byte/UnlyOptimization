package com.unlymng.optimization.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.unlymng.optimization.ui.theme.NeonCyan
import com.unlymng.optimization.ui.theme.SurfaceDark
import com.unlymng.optimization.ui.viewmodel.SettingsViewModel

/**
 * Экран Settings - настройки приложения и информация
 */
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val autoBoost by viewModel.autoBoost.collectAsState()
    val language by viewModel.language.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "НАСТРОЙКИ",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = NeonCyan,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Auto Boost
        SettingCard {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        "Автобуст при запуске игры",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        "Автоматически оптимизировать при запуске",
                        color = Color.Gray,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                Switch(
                    checked = autoBoost,
                    onCheckedChange = { viewModel.setAutoBoost(it) }
                )
            }
        }

        // Language
        SettingCard(
            onClick = { viewModel.showLanguageDialog() }
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
                        "Язык",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        language,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Navigate",
                    tint = NeonCyan
                )
            }
        }

        // Telegram Channel
        SettingCard(
            onClick = { viewModel.openTelegramChannel(context) }
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
                        "Telegram канал",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        "@unlymng",
                        color = Color.Gray,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Navigate",
                    tint = NeonCyan
                )
            }
        }

        // About
        SettingCard {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    "О приложении",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    "Версия 1.0.0",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    "Лицензия: Бесплатная",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
fun SettingCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(enabled = onClick != null, onClick = { onClick?.invoke() }),
        colors = CardDefaults.cardColors(
            containerColor = SurfaceDark
        )
    ) {
        content()
    }
}
