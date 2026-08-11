package com.unlymng.optimization.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.unlymng.optimization.ui.theme.NeonCyan
import com.unlymng.optimization.ui.theme.NeonPurple
import com.unlymng.optimization.ui.theme.SurfaceDark
import com.unlymng.optimization.ui.theme.SuccessGreen
import com.unlymng.optimization.ui.viewmodel.BoostViewModel

/**
 * Экран Boost - главный экран приложения с большой кнопкой для оптимизации
 */
@Composable
fun BoostScreen(
    viewModel: BoostViewModel = hiltViewModel()
) {
    val isBoosting by viewModel.isBoosting.collectAsState()
    val boostResult by viewModel.boostResult.collectAsState()
    val freedMemory by viewModel.freedMemory.collectAsState()
    val installedGames by viewModel.installedGames.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadInstalledGames()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Заголовок
        Text(
            text = "UNLY OPTIMIZATION",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = NeonCyan,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 32.dp)
        )

        // Большая круглая кнопка BOOST
        BoostButton(
            isBoosting = isBoosting,
            onClick = { viewModel.performBoost() },
            modifier = Modifier
                .size(200.dp)
                .padding(32.dp)
        )

        // Результат буста
        if (boostResult) {
            BoostResultCard(
                freedMemory = freedMemory,
                onLaunchGame = { /* Запуск PUBG */ },
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        // Список установленных игр
        Text(
            text = "Ваши игры",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(vertical = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(installedGames.size) { index ->
                GameCard(
                    gameName = installedGames[index],
                    onClick = { viewModel.boostAndLaunchGame(installedGames[index]) }
                )
            }
        }
    }
}

@Composable
fun BoostButton(
    isBoosting: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val animatedColor by animateColorAsState(
        targetValue = if (isBoosting) NeonPurple else NeonCyan,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Button(
        onClick = onClick,
        modifier = modifier
            .clip(CircleShape),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = animatedColor
        ),
        enabled = !isBoosting
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Boost",
                tint = Color.Black,
                modifier = Modifier.size(48.dp)
            )
            if (!isBoosting) {
                Text(
                    text = "BOOST",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun BoostResultCard(
    freedMemory: Long,
    onLaunchGame: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = SurfaceDark
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "✓ Успешно!",
                color = SuccessGreen,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Освобождено $freedMemory МБ",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
            Button(
                onClick = onLaunchGame,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = NeonCyan
                )
            ) {
                Text("Запустить PUBG", color = Color.Black)
            }
        }
    }
}

@Composable
fun GameCard(
    gameName: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = SurfaceDark
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = gameName.take(3),
                fontSize = 12.sp,
                color = NeonCyan,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
        }
    }
}
