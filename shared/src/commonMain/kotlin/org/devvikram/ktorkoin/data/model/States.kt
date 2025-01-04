package org.devvikram.ktorkoin.data.model

import kotlinx.serialization.Serializable

@Serializable
data class States(
    val stateId: String,
    val stateName: String
)