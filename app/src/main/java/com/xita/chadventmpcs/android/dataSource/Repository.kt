package com.xita.chadventmpcs.android.dataSource

import com.xita.chadventmpcs.android.BuildConfig
import com.xita.chadventmpcs.android.dataSource.api.RetrofitInstance
import com.xita.chadventmpcs.android.models.Account
import com.xita.chadventmpcs.android.models.Member
import com.xita.chadventmpcs.android.models.UserLogin
import com.xita.chadventmpcs.android.models.UserResponse

class Repository {
    val apiKey = BuildConfig.API_KEY


    suspend fun getMembers(): List<Member> {
        return RetrofitInstance.chadventAPI.getMembers(apiKey)
    }

    suspend fun getAccounts(): List<Account> {
        return RetrofitInstance.chadventAPI.getAccounts(apiKey)
    }

    suspend fun logUserIn(userLogin: UserLogin): UserResponse {
        return RetrofitInstance.chadventAPI.logUserIn(
            apiKey = apiKey,
            username = userLogin.username,
            password = userLogin.password
        )
    }
}