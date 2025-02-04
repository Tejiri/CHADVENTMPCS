package com.xita.chadventmpcs.android.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SettingsScreenViewModel : ViewModel() {
    var isLoading by mutableStateOf(false)
    var notificationSwitchEnabled by mutableStateOf(false)
    var isError by mutableStateOf(false)
    var errorMessage by mutableStateOf("")

    fun notificationClicked(newSwitchValue:Boolean) {
        if (notificationSwitchEnabled) {
            notificationSwitchEnabled = newSwitchValue
        } else {
            notificationSwitchEnabled = newSwitchValue
        }
    }

    fun reportAnIssueClicked() {
        isError = true
        errorMessage = "This feature is coming soon!!!!"
    }

    fun frequentlyAskedQuestionsClicked() {
        isError = true
        errorMessage = "This feature is coming soon!!!!"
    }

    fun lightOrDarkModeClicked() {
        isError = true
        errorMessage = "This feature is coming soon!!!!"
    }
}