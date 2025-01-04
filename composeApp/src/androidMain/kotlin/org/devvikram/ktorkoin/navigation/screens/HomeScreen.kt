package org.devvikram.ktorkoin.navigation.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.devvikram.ktorkoin.presentation.viewmodels.StateViewmodel
import org.devvikram.ktorkoin.presentation.viewmodels.UIState

@Composable
fun HomeScreen(modifier: Modifier, viewModel: StateViewmodel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current


    when (uiState) {
        is UIState.Loading -> {
            // Show loading state
            CircularProgressIndicator()
        }

        is UIState.Success -> {
            // Show success state
            val data = (uiState as UIState.Success).data
            // create a spinner to load the data

        }

        is UIState.Error -> {
            // Show error state
        }

        else -> {
            // Show default state
        }
    }


}