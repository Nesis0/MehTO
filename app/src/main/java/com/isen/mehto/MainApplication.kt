package com.isen.mehto

import android.app.Application
import com.isen.mehto.data.OfflineConfigRepository
import com.isen.mehto.data.repositories.WeatherRepository
import com.isen.mehto.data.repositories.impl.WeatherRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                repositoryModule,
                dataSourcesModule,
                apiService
            )
        }
    }
}

val repositoryModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl() }
    single<OfflineConfigRepository> { OfflineConfigRepository(get()) }
}

val dataSourcesModule = module { }

val apiService = module { }
