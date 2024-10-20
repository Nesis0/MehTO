package com.isen.mehto.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.isen.mehto.data.repositories.db.impl.OfflineConfigRepository
import com.isen.mehto.data.entity.Config
import kotlinx.coroutines.launch

data class ConfigItem (
    val config: MutableState<Config>,
    val isExpanded: MutableState<Boolean>,
)

class SettingsViewModel(private val configRepository: OfflineConfigRepository) : ViewModel() {
    private val _settings = mutableStateOf(listOf<ConfigItem>())
    val settings = _settings

    init {
        viewModelScope.launch {
            configRepository.initConfig()
            loadConfigs()
        }
    }

    private suspend fun loadConfigs() {
        _settings.value = configRepository.getAllConfigs().map {
            ConfigItem(mutableStateOf(it), mutableStateOf(false))
        }
    }

    fun saveSetting(value: String, configItem: ConfigItem){
        configItem.isExpanded.value = false
        configItem.config.value = Config(configItem.config.value.type, value)

        viewModelScope.launch {
            configRepository.update(configItem.config.value)
        }
    }

    fun resetSettings(){
        viewModelScope.launch {
            configRepository.clearConfig()
            configRepository.initConfig()
            loadConfigs()
        }
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