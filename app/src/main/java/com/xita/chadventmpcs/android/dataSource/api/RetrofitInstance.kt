package com.xita.chadventmpcs.android.dataSource.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance{

    private const val BASE_URL = " https://chadventmpcs.herokuapp.com/json/" // Example URL

    val chadventAPI: ChadventAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChadventAPI::class.java)
    }
}