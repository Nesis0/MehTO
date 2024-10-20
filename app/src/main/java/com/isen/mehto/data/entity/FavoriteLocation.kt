package com.isen.mehto.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteLocation(
    @PrimaryKey(autoGenerate = true)
    val location_id: Int? = null,

    @ColumnInfo(name = "preference_index")
    val preference_index: Int,

    @ColumnInfo(name = "display_name")
    val display_name: String,

    @ColumnInfo(name = "latitude")
    val latitude: Double,

    @ColumnInfo(name = "longitude")
    val longitude: Double
)