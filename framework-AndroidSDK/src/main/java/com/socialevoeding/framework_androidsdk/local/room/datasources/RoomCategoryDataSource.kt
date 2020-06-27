package com.socialevoeding.framework_androidsdk.local.room.datasources

import com.socialevoeding.data.datasources.local.database.CategoryLocalDataSource
import com.socialevoeding.data_entities.CategoryEntity
import com.socialevoeding.framework_androidsdk.local.room.dao.CategoryDao
import com.socialevoeding.framework_androidsdk.local.room.mappers.RoomCategoryMapper

class RoomCategoryDataSource(private val categoryDao: CategoryDao) : CategoryLocalDataSource {
    override suspend fun getCategories(): List<CategoryEntity> {
        return categoryDao.getCategories().map { RoomCategoryMapper.mapFromRoomEntity(it) }
    }

    override suspend fun insert(categories: List<CategoryEntity>) {
        categoryDao.insertMockData(RoomCategoryMapper.mapToRoomEntities(categories))
    }
}