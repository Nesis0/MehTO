package com.isen.mehto.data.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ConfigDAO {
    @Query("SELECT * FROM config")
    suspend fun getAllConfigs(): List<Config>

    @Insert
    suspend fun insert(config: Config)

    @Query("INSERT OR IGNORE INTO config (type, value) VALUES (:type, :value)")
    suspend fun initInsert(type: ConfigType, value: String)

    @Query("SELECT value FROM config WHERE type = :type")
    suspend fun read(type: String): String?

    @Update
    suspend fun update(config: Config)

    @Delete
    suspend fun delete(config: Config)

    @Query("DELETE FROM config")
    suspend fun clearConfig()
}