package org.devvikram.ktorkoin.permissions

import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import org.devvikram.ktorkoin.permission.LocationPermission

class AndroidLocationPermission(
    private val context: Context,
    private val activityResultLauncher: ActivityResultLauncher<String>
) : LocationPermission {
    override fun hasLocationPermission(): Boolean {
        return android.Manifest.permission.ACCESS_FINE_LOCATION.let {
            context.checkSelfPermission(it) == android.content.pm.PackageManager.PERMISSION_GRANTED
        }
    }

    override fun requestLocationPermission(granted: (Boolean) -> Unit) {
        activityResultLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
    }


    override fun shouldShowRationale(): Boolean {
        return if (context is android.app.Activity) {
            androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        } else {
            false
        }
    }


}