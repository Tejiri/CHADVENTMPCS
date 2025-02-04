package com.xita.chadventmpcs.android.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = false)
    val _id: String,
    val transactiontype: String,
    val account: String,
    val amount: String,
    val narration: String,
    val date: String,
)
