package org.devvikram.ktorkoin.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.devvikram.ktorkoin.navigation.screens.CanvasScreen
import org.devvikram.ktorkoin.navigation.screens.ConversationScreen
import org.devvikram.ktorkoin.navigation.screens.HomeScreen
import org.devvikram.ktorkoin.navigation.screens.UserScreen
import org.devvikram.ktorkoin.presentation.viewmodels.StateViewmodel
import org.devvikram.ktorkoin.presentation.viewmodels.UserViewModel
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
            HomeScreen(
                modifier = modifier,
                navHostController = navController,
                viewModel = viewModel
            )
        }
        composable<Destination.CanvasD> {
            CanvasScreen(modifier = modifier)
        }
        composable<Destination.User> {
            val viewmodel = koinViewModel<UserViewModel>()
            UserScreen(modifier = modifier,viewmodel)
        }
        composable<Destination.Conversation> {
            ConversationScreen()
        }
    }
}




