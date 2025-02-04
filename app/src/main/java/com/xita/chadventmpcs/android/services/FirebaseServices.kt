package com.xita.chadventmpcs.android.services

import android.app.Activity
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.firebase.remoteconfig.remoteConfig
import com.xita.chadventmpcs.android.R

class FirebaseServices {
    val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
    val configSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds = 60
    }

    init {
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
    }


    fun validateAppVersion(
        activity: Activity,
        onUpToDate: () -> Unit,
        onUpdate: () -> Unit
    ) {

// Inside an Activity or other Context-aware class
        val packageInfo = activity.packageManager.getPackageInfo(activity.packageName, 0)

// versionName (String)
        val versionName = packageInfo.versionName

// versionCode is stored as a Long in newer Android versions
        val versionCode = if (android.os.Build.VERSION.SDK_INT >= 28) {
            packageInfo.longVersionCode
        } else {
            @Suppress("DEPRECATION")
            packageInfo.versionCode.toLong()
        }
//Log.i("AppVersion","Long version code ${packageInfo.longVersionCode}")
        Log.i("AppVersion", "versionName = $versionName, versionCode = $versionCode")

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    if (FirebaseServices().remoteConfig.getLong("app_version") == versionCode) {
                        onUpToDate()
                    } else {
                        onUpdate()
                    }
                } else {
                    Log.i("MYINFO", "Something happened")
                }

            }

    }
}