package com.xita.chadventmpcs.android.models

data class User(
    val _id: String,
    val username: String,
    val salt: String,
    val hash: String,
    val __v: Int
)

data class UserResponse(val message:String,val user:User?)
