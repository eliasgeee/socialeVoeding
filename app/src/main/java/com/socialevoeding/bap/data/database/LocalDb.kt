package com.socialevoeding.bap.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.socialevoeding.bap.data.dao.CategoryDao
import com.socialevoeding.bap.data.dao.PlaceDao
import com.socialevoeding.bap.data.entities.CategoryEntity
import com.socialevoeding.bap.data.entities.PlaceEntity

@Database(entities = [CategoryEntity::class, PlaceEntity::class], version = 4, exportSchema = false)
abstract class LocalDb : RoomDatabase() {
    abstract val categoryDao: CategoryDao
    abstract val placeDao: PlaceDao
}

private lateinit var INSTANCE: LocalDb

fun getDatabase(context: Context): LocalDb {
    synchronized(LocalDb::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                LocalDb::class.java,
                "socialevoeding_database")
                .fallbackToDestructiveMigration()
                .build()
        }
        return INSTANCE
    }
}