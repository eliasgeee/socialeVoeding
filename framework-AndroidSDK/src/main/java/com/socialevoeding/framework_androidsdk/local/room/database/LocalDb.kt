package com.socialevoeding.framework_androidsdk.local.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.socialevoeding.framework_androidsdk.local.room.dao.CategoryDao
import com.socialevoeding.framework_androidsdk.local.room.dao.PlaceDao
import com.socialevoeding.framework_androidsdk.local.room.entities.RoomCategoryEntity
import com.socialevoeding.framework_androidsdk.local.room.entities.RoomPlaceEntity

@Database(entities = [RoomCategoryEntity::class, RoomPlaceEntity::class], version = 6, exportSchema = false)
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