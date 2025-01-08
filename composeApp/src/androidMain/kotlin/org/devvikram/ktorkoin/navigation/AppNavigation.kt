package org.devvikram.ktorkoin.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.devvikram.ktorkoin.navigation.screens.CanvasScreen
import org.devvikram.ktorkoin.navigation.screens.HomeScreen
import org.devvikram.ktorkoin.presentation.viewmodels.StateViewmodel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destination.Home

    ) {
        composable<Destination.Home> {
            val viewModel: StateViewmodel = koinViewModel()
            HomeScreen(modifier = modifier, viewModel = viewModel, navigateToCanvaScreen = {
                navController.navigate(Destination.CanvasD)
            })
        }
        composable<Destination.CanvasD> {
            CanvasScreen(modifier = modifier)
        }
    }
}




