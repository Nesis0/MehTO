package data

import data.entity.Config

interface ConfigRepository {
    suspend fun getAllConfigs(): List<Config>

    suspend fun insertConfig(config: Config)

    suspend fun getConfig(key: String): Config?

    suspend fun updateConfig(config: Config)

    suspend fun deleteConfig(config: Config)

    suspend fun clearConfig()
}