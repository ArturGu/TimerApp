package com.example.timerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimerScreen()
        }
    }
}

@Composable
fun TimerScreen() {
    var time by remember { mutableStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(text = "Time: $time сек", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(32.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = {
                if (!isRunning) {
                    isRunning = true
                    scope.launch {
                        while (isRunning) {
                            delay(1000L)
                            time++
                        }
                    }
                }
            }) {
                Text("Старт")
            }

            Button(onClick = { isRunning = false }) {
                Text("Стоп")
            }

            Button(onClick = {
                isRunning = false
                time = 0
            }) {
                Text("Скинути")
            }
        }
    }
}