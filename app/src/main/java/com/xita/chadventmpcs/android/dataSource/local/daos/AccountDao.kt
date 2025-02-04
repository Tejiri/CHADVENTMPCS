package com.xita.chadventmpcs.android.dataSource.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xita.chadventmpcs.android.models.entities.AccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(accountEntity: AccountEntity)

    @Query("SELECT * from accounts WHERE username = :username LIMIT 1")
    fun getAccountByUsername(username: String): Flow<AccountEntity>

    @Query("SELECT * from accounts ORDER BY username ASC")
    fun getAllAccounts(): Flow<List<AccountEntity>>
}