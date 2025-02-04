package com.xita.chadventmpcs.android.dataSource.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xita.chadventmpcs.android.models.entities.MemberEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MemberDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(memberEntity: MemberEntity)

    @Query("SELECT * from members WHERE _id = :id")
    fun getMemberById(id: Int): Flow<MemberEntity>

    @Query("SELECT * from members ORDER BY firstname ASC")
    fun getAllMembers(): Flow<List<MemberEntity>>

    @Query("DELETE FROM members")
    suspend fun clearMembers()
}