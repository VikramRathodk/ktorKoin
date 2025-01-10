package org.devvikram.ktorkoin.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
sealed class Destination {

    @Serializable
    data object Home : Destination()

    @Serializable
    data object CanvasD : Destination()

    @Serializable
    data object User : Destination()

    @Serializable
    @SerialName("Conversation")
    data object Conversation: Destination()


}