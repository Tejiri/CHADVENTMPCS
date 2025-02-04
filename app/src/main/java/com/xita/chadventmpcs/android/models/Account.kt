package com.xita.chadventmpcs.android.models


data class Transaction(
    val _id: String,
    val transactiontype: String,
    val account: String,
    val amount: String,
    val narration: String,
    val date: String,
)

data class Account(
    val _id: String,
    val username: String,
    val sharecapital: String,
    val thriftsavings: String,
    val specialdeposit: String,
    val commoditytrading: String,
    val fine: String,
    val loan: String,
    val projectfinancing: String,
    val transactions: List<Transaction>
)
