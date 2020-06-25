package com.socialevoeding.framework_androidsdk.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "places", primaryKeys = ["latitude", "longitude"])
class RoomPlaceEntity(
    val name: String = "",
    val telephoneNumber: String = "",
    val address: String = "",
    val webUrl: String = "",
    val img: String,
    val latitude: Double,
    val longitude: Double,
    val city: String,
    @ForeignKey(entity = RoomCategoryEntity::class, onDelete = ForeignKey.CASCADE, childColumns = ["categoryId"], parentColumns = ["id"])
    val categoryId: Int = 0
)