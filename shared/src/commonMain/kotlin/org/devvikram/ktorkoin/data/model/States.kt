package org.devvikram.ktorkoin.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class States(
    @SerialName("states_id")
    val stateId: String,
    @SerialName("states_name")
    val stateName: String,
    @SerialName("state_code")
    val stateCode: String,
    @SerialName("countries_id")
    val countriesId: String,
    @SerialName("states_status")
    val statesStatus: String
)