package data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Config(
    @PrimaryKey
    val key: String,

    @ColumnInfo(name = "key")
    val value: String

)

