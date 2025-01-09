package org.devvikram.ktorkoin.navigation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import org.devvikram.ktorkoin.database.User
import org.devvikram.ktorkoin.presentation.viewmodels.UserViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserScreen(
    modifier: Modifier = Modifier,
    viewModel: UserViewModel = koinViewModel()
) {
    var nameInput by remember { mutableStateOf(TextFieldValue("")) }
    var emailInput by remember { mutableStateOf(TextFieldValue("")) }

    val users by viewModel.users.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when (uiState) {
            is UserViewModel.UIState.LOADING -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            is UserViewModel.UIState.SUCCESS -> {
                Text(
                    text = (uiState as UserViewModel.UIState.SUCCESS).message,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            is UserViewModel.UIState.ERROR -> {
                Text(
                    text = (uiState as UserViewModel.UIState.ERROR).message,
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            UserViewModel.UIState.IDEAL -> {
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Add User", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = nameInput,
            onValueChange = { nameInput = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = emailInput,
            onValueChange = { emailInput = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (nameInput.text.isNotEmpty() && emailInput.text.isNotEmpty()) {
                    viewModel.saveUser(
                        User(
                            id = 0L,
                            name = nameInput.text,
                            email = emailInput.text
                        )
                    )
                    nameInput = TextFieldValue("")
                    emailInput = TextFieldValue("")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save User")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Users", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(users) { user ->
                UserItem(user = user,
                    deleteUser = {
                        viewModel.deleteUser(user)
                    })
            }
        }
    }
}

@Composable
fun UserItem(
    user: User,
    deleteUser: (User) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "Name: ${user.name}", style = MaterialTheme.typography.body1)
                Text(text = "Email: ${user.email}", style = MaterialTheme.typography.body2)
            }

            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete User",
                modifier = Modifier.clickable { deleteUser(user) }
            )
        }
    }
}

