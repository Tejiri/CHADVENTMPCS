package com.xita.chadventmpcs.android.dataSource

import com.xita.chadventmpcs.android.BuildConfig
import com.xita.chadventmpcs.android.dataSource.api.RetrofitInstance
import com.xita.chadventmpcs.android.models.Account
import com.xita.chadventmpcs.android.models.Member
import com.xita.chadventmpcs.android.models.UserLogin
import com.xita.chadventmpcs.android.models.UserResponse

class Repository {
    val api_key = "5fd2ac39-15d3-4935-a36a-509597984923"
    val apiKey = BuildConfig.API_KEY


    suspend fun getMembers(): List<Member> {
        return RetrofitInstance.chadventAPI.getMembers(api_key)
    }

    suspend fun getAccounts(): List<Account> {
        return RetrofitInstance.chadventAPI.getAccounts(api_key)
    }

    suspend fun logUserIn(userLogin: UserLogin): UserResponse {
        return RetrofitInstance.chadventAPI.logUserIn(
            apiKey = api_key,
            username = userLogin.username,
            password = userLogin.password
        )
    }
}