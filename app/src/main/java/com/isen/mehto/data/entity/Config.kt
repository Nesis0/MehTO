package com.isen.mehto.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Config(
    @PrimaryKey
    @ColumnInfo(name = "type")
    val type: ConfigType,

    @ColumnInfo(name = "value")
    val value: String
)

enum class ConfigType(val displayName: String) {
    UNIT("Temperature unit"),
    ENABLE_GEOLOCATION("Geolocation"),
}
