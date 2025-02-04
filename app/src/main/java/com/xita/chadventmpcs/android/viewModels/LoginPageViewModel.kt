package com.xita.chadventmpcs.android.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xita.chadventmpcs.android.dataSource.Repository
import com.xita.chadventmpcs.android.models.Account
import com.xita.chadventmpcs.android.models.Member
import com.xita.chadventmpcs.android.models.User
import com.xita.chadventmpcs.android.models.UserLogin
import kotlinx.coroutines.launch

class LoginPageViewModel : ViewModel() {
    private var repository = Repository()
    var membersResponse = mutableStateListOf<Member>()
    var accountsResponse = mutableStateListOf<Account>()
    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var user by mutableStateOf<User?>(null)
    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)
    var errorMessage by mutableStateOf("")


//    init {
//        getMembers()
//    }

    fun usernameChange(newUsername: String) {
        username = newUsername
    }

    fun passwordChange(newPassword: String) {
        password = newPassword
    }

    fun logUserIn(onSuccess: () -> Unit) {
        isLoading = true
        if (username.isEmpty() || password.isEmpty()) {
            isError = true
            errorMessage = "Username or password cannot be empty"
            isLoading = false
        } else {

            viewModelScope.launch {
                try {
                    val response = repository.logUserIn(UserLogin(username, password))
                    val loggedInUser: User? = response.user

                    if (loggedInUser != null) {
                        user = loggedInUser
                        getMembersAndAccount(onSuccess)
                    }

                    Log.i("MYINFO", response.toString())
                } catch (e: Exception) {
                    isError = true
                    errorMessage = e.localizedMessage
                    Log.i("MYEXCEPTION", e.localizedMessage)

                    isLoading = false
                } finally {

                }
            }
        }
    }

    fun getMembersAndAccount(onSuccess: () -> Unit) {

        viewModelScope.launch {
            try {
                val response = repository.getMembers()
                val response2 = repository.getAccounts()


                accountsResponse.clear()
                accountsResponse.addAll(response2)

                membersResponse.clear()
                membersResponse.addAll(response)
                onSuccess()
//                for (i in accountsResponse) {
//                    Log.i("MYINFO", i.toString())
//                }
            } catch (e: Exception) {
                Log.i("MYEXCEPTION", e.localizedMessage)
            } finally {

            }

        }
    }
}