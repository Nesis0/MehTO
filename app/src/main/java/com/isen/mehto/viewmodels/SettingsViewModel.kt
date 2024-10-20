package com.isen.mehto.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.isen.mehto.data.repositories.db.impl.OfflineConfigRepository
import com.isen.mehto.data.entity.Config
import kotlinx.coroutines.launch

class SettingsViewModel(private val configRepository: OfflineConfigRepository) : ViewModel() {
    private val _settings = mutableStateOf(listOf<Config>())
    val settings = _settings

    init {
        viewModelScope.launch {
            configRepository.initConfig()
            _settings.value = configRepository.getAllConfigs()
        }
    }

    suspend fun saveSetting(config: Config){
        configRepository.insert(config)
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