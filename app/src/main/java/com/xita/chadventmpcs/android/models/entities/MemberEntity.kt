package com.xita.chadventmpcs.android.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "members")
data class MemberEntity(
    @PrimaryKey(autoGenerate = false)
    val _id: String,
    val username: String,
    val title: String,
    val firstname: String,
    val lastname: String,
    val middlename: String,
    val position: String,
    val membershipstatus: String,
    val loanapplicationstatus: String,
    val phonenumber: String,
    val email: String,
    val address: String,
    val gender: String,
    val occupation: String,
    val nextofkin: String,
    val nextofkinaddress: String,
    val bank: String,
    val accountnumber: String,
    val __v: Int,
)