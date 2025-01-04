package org.devvikram.ktorkoin.domain.repository

import org.devvikram.ktorkoin.data.model.States
import org.devvikram.ktorkoin.utils.NetworkError
import org.devvikram.ktorkoin.utils.Result

interface StateRepository {
    suspend fun getStates(): Result<List<States>, NetworkError>
}