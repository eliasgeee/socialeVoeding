package com.socialevoeding.framework_androidsdk.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "places")
class RoomPlaceEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "place_id")
    val id: Int = 0,
    val name: String = "",
    val distance: Int = 0,
    val telephoneNumber: String = "",
    val address: String = "",
    val webUrl: String = "",
    val isOpen: Boolean = false,
    val img: String,
    val latitude: Double,
    val longitude: Double,
    val city: String,
    val categoryId: Int = 0
)