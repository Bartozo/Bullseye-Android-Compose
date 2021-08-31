package com.bartozo.bullseye

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.bartozo.bullseye.ui.screens.HomeScreen
import com.bartozo.bullseye.ui.theme.BullseyeTheme
import com.bartozo.bullseye.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BullseyeTheme {
                HomeScreen(viewModel = viewModel)
            }
        }
    }
}

