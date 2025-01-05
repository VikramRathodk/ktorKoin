package org.devvikram.ktorkoin.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.devvikram.ktorkoin.data.model.States
import org.devvikram.ktorkoin.domain.usecases.StateUseCase
import org.devvikram.ktorkoin.utils.onError
import org.devvikram.ktorkoin.utils.onSuccess

class StateViewmodel(
    private val stateUseCase: StateUseCase

):ViewModel() {

    private val _uiState = MutableStateFlow<UIState>(UIState.Empty)
    val uiState = _uiState.asStateFlow()

  fun getStates(){
      viewModelScope.launch(Dispatchers.IO) {
          _uiState.update { UIState.Loading }
          try {
              val result = stateUseCase()
              result.onSuccess {
                  states->
                  _uiState.update { UIState.Success(states) }
              }.onError { networkError ->
                  println("Unexpected error this ${networkError.name}")
                  _uiState.update { UIState.Error(networkError.name) }
              }
          } catch (e: Exception) {
              println("Error: ${e.message}")
              e.printStackTrace()
              _uiState.update { UIState.Error(it.toString()) }
          }
      }


  }

}
sealed class UIState{
    data object Loading: UIState()
    data class Success(val data: List<States>): UIState()
    data class Error(val error: String): UIState()
    data object Empty: UIState()

}

