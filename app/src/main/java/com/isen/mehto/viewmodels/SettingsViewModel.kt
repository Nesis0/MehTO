package com.isen.mehto.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.isen.mehto.data.repositories.db.impl.OfflineConfigRepository
import com.isen.mehto.data.entity.Config

class SettingsViewModel(private val configRepository: OfflineConfigRepository) : ViewModel() {
    suspend fun saveSetting(config: Config){
        configRepository.insert(config)
    }

    suspend fun getAllConfigs(){
        configRepository.getAllConfigs()
    }

    suspend fun getConfig(key: String): String?{
        return configRepository.read(key)
    }

    suspend fun updateConfig(config: Config){
        configRepository.update(config)
    }

    suspend fun deleteConfig(config: Config){
        configRepository.delete(config)
    }

    suspend fun clearConfig(){
        configRepository.clearConfig()
    }

    class ViewModelFactory(
        private val configRepository: OfflineConfigRepository,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
                return SettingsViewModel(
                    configRepository = configRepository,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}