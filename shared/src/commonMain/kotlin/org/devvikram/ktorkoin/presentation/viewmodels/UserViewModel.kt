package org.devvikram.ktorkoin.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.devvikram.ktorkoin.database.User
import org.devvikram.ktorkoin.sqldelight.UserRepository

class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users = _users.asStateFlow()

    private val _selectedUser = MutableStateFlow<User?>(null)
    val selectedUser = _selectedUser.asStateFlow()

    private val _uiState = MutableStateFlow<UIState>(UIState.IDEAL)
    val uiState = _uiState.asStateFlow()
    init {
        getAllUsers()
    }

    fun saveUser(user: User) {
        viewModelScope.launch {
            _uiState.value = UIState.LOADING
            try {
                userRepository.insertUser(user.name, user.email)
                getAllUsers()
                _uiState.value = UIState.SUCCESS("User saved successfully!")
            } catch (e: Exception) {
                _uiState.value = UIState.ERROR("Failed to save user: ${e.message}")
            }
        }
    }

    fun getUserById(id: Long) {
        viewModelScope.launch {
            _uiState.value = UIState.LOADING
            try {
                val user = userRepository.getUserById(id)
                _selectedUser.value = user
                _uiState.value = UIState.SUCCESS("User fetched successfully!")
            } catch (e: Exception) {
                _selectedUser.value = null
                _uiState.value = UIState.ERROR("Failed to fetch user: ${e.message}")
            }
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            _uiState.value = UIState.LOADING
            try {
                userRepository.deleteUser(user.id)
                getAllUsers()
                _uiState.value = UIState.SUCCESS("User Deleted Successfully!")
            } catch (e: Exception) {
                _uiState.value = UIState.ERROR("Failed to delete user: ${e.message}")
            }
        }
    }

    private fun getAllUsers() {
        viewModelScope.launch {
            _uiState.value = UIState.LOADING
            try {
                val users = userRepository.getAllUsers()
                _users.value = users
                _uiState.value = UIState.SUCCESS("Users fetched successfully!")
            } catch (e: Exception) {
                _users.value = emptyList()
                _uiState.value = UIState.ERROR("Failed to fetch users: ${e.message}")
            }
        }
    }

    sealed class UIState {
        data object IDEAL : UIState()
        data object LOADING : UIState()
        data class SUCCESS(val message: String) : UIState()
        data class ERROR(val message: String) : UIState()
    }
}
