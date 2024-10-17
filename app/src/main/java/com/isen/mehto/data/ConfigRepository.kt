package com.isen.mehto.data

import com.isen.mehto.data.entity.Config

interface ConfigRepository {
    suspend fun getAllConfigs(): List<Config>

    suspend fun insert(config: Config)

    suspend fun read(key: String): String?

    suspend fun update(config: Config)

    suspend fun delete(config: Config)

    suspend fun clearConfig()
}