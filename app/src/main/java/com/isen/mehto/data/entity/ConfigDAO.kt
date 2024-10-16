package com.isen.mehto.data.entity

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface ConfigDAO {
    @Query("SELECT * FROM config")
    suspend fun getAllConfigs(): List<Config>

    @Insert
    suspend fun insert(config: Config)

    @Query("SELECT * FROM config WHERE name = :name")
    suspend fun getConfig(name: String): Config?

    @Update
    suspend fun updateConfig(config: Config)

    @Delete
    suspend fun delete(config: Config)

    @Query("DELETE FROM config")
    suspend fun clearConfig()
}