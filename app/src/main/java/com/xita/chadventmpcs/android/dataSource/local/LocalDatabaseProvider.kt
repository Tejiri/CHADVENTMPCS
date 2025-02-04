package com.xita.chadventmpcs.android.dataSource.local

import android.content.Context
import androidx.room.Room

object CHadventLocalDatabaseProvider{

    private var INSTANCE: LocalDatabase? = null

    fun getDatabase(context: Context): LocalDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                LocalDatabase::class.java,
                "user_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}