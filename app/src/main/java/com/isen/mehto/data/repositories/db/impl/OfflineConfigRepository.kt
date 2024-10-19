package com.isen.mehto.data.repositories.db.impl

import com.isen.mehto.data.entity.Config
import com.isen.mehto.data.entity.ConfigDAO
import com.isen.mehto.data.repositories.db.ConfigRepository

class OfflineConfigRepository(private val configDAO: ConfigDAO) : ConfigRepository {
    override suspend fun getAllConfigs(): List<Config> {
        return configDAO.getAllConfigs()
    }

    override suspend fun insert(config: Config) {
        return configDAO.insert(config)
    }

    override suspend fun initInsert(config: Config){
        return configDAO.initInsert(config.name, config.value)
    }

    override suspend fun read(key: String): String? {
        return configDAO.read(key)
    }

    override suspend fun update(config: Config) {
        return configDAO.update(config)
    }

    override suspend fun delete(config: Config) {
        return configDAO.delete(config)
    }

    override suspend fun clearConfig() {
        return configDAO.clearConfig()
    }

    suspend fun initConfig(){
        configDAO.initInsert("unit", "celsius")
        configDAO.initInsert("language", "fr")
        configDAO.initInsert("geolocation", "true")
    }
}