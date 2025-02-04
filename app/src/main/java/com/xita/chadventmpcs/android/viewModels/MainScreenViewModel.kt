package com.xita.chadventmpcs.android.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainScreenViewModel : ViewModel() {
    var homeSelected by mutableStateOf(false)
    var transactionsSelected by mutableStateOf(false)
    var contactsSelected by mutableStateOf(false)
    var settingsSelected by mutableStateOf(false)
    var membersPostsSelected by mutableStateOf(true)

    fun onHomeNavSelected() {
        homeSelected = true
        transactionsSelected = false
        contactsSelected = false
        settingsSelected = false
        membersPostsSelected = false
    }

    fun onTransactionsNavSelected() {
        transactionsSelected = true
        homeSelected = false
        contactsSelected = false
        settingsSelected = false
        membersPostsSelected = false
    }

    fun onPhoneNavSelected() {
        contactsSelected = true
        homeSelected = false
        transactionsSelected = false
        settingsSelected = false
        membersPostsSelected = false
    }

    fun onSettingsNavSelected() {
        settingsSelected = true
        homeSelected = false
        transactionsSelected = false
        contactsSelected = false
        membersPostsSelected = false
    }

    fun onMembersPostsSelected() {
        membersPostsSelected = true
        settingsSelected = false
        homeSelected = false
        transactionsSelected = false
        contactsSelected = false
    }
}