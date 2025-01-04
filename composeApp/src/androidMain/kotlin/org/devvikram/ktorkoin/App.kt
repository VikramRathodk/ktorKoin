package org.devvikram.ktorkoin

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import ktorkoin.composeapp.generated.resources.Res
import ktorkoin.composeapp.generated.resources.compose_multiplatform
import org.devvikram.ktorkoin.navigation.AppNavigation
import org.koin.androidx.compose.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
         AppNavigation()
    }

}

