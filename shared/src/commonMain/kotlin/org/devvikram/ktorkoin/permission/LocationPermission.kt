package org.devvikram.ktorkoin.permission

interface LocationPermission {
    fun hasLocationPermission(): Boolean
    fun requestLocationPermission(granted: (Boolean) -> Unit)
    fun shouldShowRationale(): Boolean
}