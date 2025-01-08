package org.devvikram.ktorkoin.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed class Destination {

    @Serializable
    data object Home : Destination()

    @Serializable
    data object CanvasD : Destination()
}