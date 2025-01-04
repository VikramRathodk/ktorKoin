package org.devvikram.ktorkoin.domain.usecases

import org.devvikram.ktorkoin.data.model.States
import org.devvikram.ktorkoin.domain.repository.StateRepository
import org.devvikram.ktorkoin.utils.NetworkError
import org.devvikram.ktorkoin.utils.Result

class StateUseCase(
    private val stateRepository: StateRepository
) {
    suspend operator  fun  invoke(): Result<List<States>, NetworkError> {
        return stateRepository.getStates()
    }
}