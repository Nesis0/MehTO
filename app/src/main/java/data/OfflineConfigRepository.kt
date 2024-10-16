package data

import data.entity.Config
import data.entity.ConfigDAO

class OfflineConfigRepository(private val configDAO: ConfigDAO) : ConfigRepository {
    override suspend fun getAllConfigs(): List<Config> {
        return configDAO.getAllConfigs()
    }

    override suspend fun insertConfig(config: Config) {
        return configDAO.insert(config)
    }

    override suspend fun getConfig(key: String): Config? {
        return configDAO.getConfig(key)
    }

    override suspend fun updateConfig(config: Config) {
        return configDAO.updateConfig(config)
    }

    override suspend fun deleteConfig(config: Config) {
        return configDAO.delete(config)
    }

    override suspend fun clearConfig() {
        return configDAO.clearConfig()
    }
}