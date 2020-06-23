package com.socialevoeding.framework_androidsdk.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.socialevoeding.domain.model.Category
import com.socialevoeding.domain.model.Place
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi

@Entity
class RoomCategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    val id: Int,
    val name: String
)
