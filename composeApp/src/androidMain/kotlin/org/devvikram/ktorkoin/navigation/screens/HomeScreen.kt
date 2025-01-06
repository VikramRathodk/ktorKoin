package org.devvikram.ktorkoin.navigation.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.devvikram.ktorkoin.presentation.viewmodels.StateViewmodel
import org.devvikram.ktorkoin.presentation.viewmodels.UIState

@Composable
fun HomeScreen(modifier: Modifier, viewModel: StateViewmodel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var stateList by remember { mutableStateOf<List<String>>(emptyList()) }


    Column (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ){
        Text(text = "Home Screen")
        Button(
            onClick = { viewModel.getStates() }
        ) {
            Text(text = "Get States")
        }
        when (uiState) {
            is UIState.Loading -> {
                CircularProgressIndicator()
            }

            is UIState.Success -> {
                stateList = (uiState as UIState.Success).data.map { it.stateName }
            }

            is UIState.Error -> {
                val error = (uiState as UIState.Error).error
                Text(text = error)
            }

            else -> {
                // ideal state do nothing
            }
        }
        StateSpinner(
            data = stateList,
            onStateSelected = { selectedState ->
                Toast.makeText(
                    context,
                    "Selected State: $selectedState",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
    }




}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StateSpinner(
    data: List<String>,
    onStateSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedState by remember { mutableStateOf("") }
    Column {


        var isClicked by remember { mutableStateOf(false) }
        val backgroundColor by animateColorAsState(
            targetValue = if (isClicked) Color.Red else Color.Blue, label = ""
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(120.dp)
                .background(backgroundColor)
                .clickable { isClicked = !isClicked }
        ) {
            Text(text = "Tab to Change Color", color = Color.White)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            var count by remember { mutableIntStateOf(0) }
            Button(onClick = { count++ }) {
                Text("Add")
            }
            AnimatedContent(
                targetState = count,
                label = "animated content"
            ) { targetCount ->
                Text(text = "Count: $targetCount")
            }
        }
        var isFirst by remember { mutableStateOf(true) }

        Crossfade(targetState = isFirst, label = "") { screen ->
            if (screen) {
                Text("First Screen")
            } else {
                Text("Second Screen")
            }
        }

        Button(onClick = { isFirst = !isFirst }) {
            Text("Switch Screen")
        }


        var expanded by remember { mutableStateOf(false) }
        val size by animateDpAsState(
            targetValue = if (expanded) 100.dp else 50.dp,
            animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
        )

        Box(
            modifier = Modifier
                .size(size)
                .background(Color.Yellow)
                .clickable { expanded = !expanded }
        )









        Text(text = "Select a State", style = MaterialTheme.typography.subtitle1)
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedState,
                onValueChange = {},
                readOnly = true,
                label = { Text("States") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier.fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                data.forEach { state ->
                    DropdownMenuItem(
                        onClick = {
                            selectedState = state
                            expanded = false
                            onStateSelected(state)
                        }
                    ) {
                        Text(text = state)
                    }
                }
            }
        }
    }
}
