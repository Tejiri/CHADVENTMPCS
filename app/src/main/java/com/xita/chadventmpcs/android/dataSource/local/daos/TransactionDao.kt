package com.xita.chadventmpcs.android.dataSource.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xita.chadventmpcs.android.models.entities.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(transactionEntity: TransactionEntity)

    @Query("SELECT * from transactions WHERE _id = :id")
    fun getTransactionById(id: Int): Flow<TransactionEntity>

    @Query("SELECT * from transactions ORDER BY date DESC")
    fun getTransactions(): Flow<List<TransactionEntity>>

    @Query("DELETE FROM transactions")
    suspend fun clearTransactions()
}