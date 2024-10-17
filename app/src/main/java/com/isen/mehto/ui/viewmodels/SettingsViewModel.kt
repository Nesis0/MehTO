package com.isen.mehto.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.isen.mehto.data.ConfigRepository
import com.isen.mehto.data.OfflineConfigRepository
import com.isen.mehto.data.entity.Config

class SettingsViewModel(private val configRepository: ConfigRepository) : ViewModel() {
    suspend fun saveSetting(config: Config){
        configRepository.insertConfig(config)
    }

    suspend fun getAllConfigs(){
        configRepository.getAllConfigs()
    }

    suspend fun getConfig(key: String): Config?{
        return configRepository.getConfig(key)
    }

    suspend fun updateConfig(config: Config){
        configRepository.updateConfig(config)
    }

    suspend fun deleteConfig(config: Config){
        configRepository.deleteConfig(config)
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