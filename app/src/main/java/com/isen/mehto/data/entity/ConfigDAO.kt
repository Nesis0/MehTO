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

    @Query("SELECT value FROM config WHERE name = :name")
    suspend fun read(name: String): String?

    @Update
    suspend fun update(config: Config)

    @Delete
    suspend fun delete(config: Config)

    @Query("DELETE FROM config")
    suspend fun clearConfig()
}