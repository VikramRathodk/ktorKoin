package org.devvikram.ktorkoin.navigation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.devvikram.ktorkoin.data.model.States
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
