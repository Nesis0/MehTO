package com.isen.mehto.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Config(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "value")
    val value: String
)

