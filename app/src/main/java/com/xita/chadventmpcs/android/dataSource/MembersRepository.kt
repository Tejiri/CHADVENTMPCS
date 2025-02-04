package com.xita.chadventmpcs.android.dataSource

import com.xita.chadventmpcs.android.models.entities.MemberEntity
import kotlinx.coroutines.flow.Flow

interface MembersRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllMembersStream(): Flow<List<MemberEntity>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getMemberStream(id: Int): Flow<MemberEntity?>

    /**
     * Insert item in the data source
     */
    suspend fun insertItem(item: MemberEntity)

    /**
     * Delete item from the data source
     */
    suspend fun deleteItem(item: MemberEntity)

    /**
     * Update item in the data source
     */
    suspend fun updateItem(item: MemberEntity)
}