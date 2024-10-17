package com.isen.mehto

import android.app.Application
import androidx.room.Room
import com.isen.mehto.data.OfflineConfigRepository
import com.isen.mehto.data.OfflineFavoriteLocationRepository
import com.isen.mehto.data.db.LocalDatabase
import com.isen.mehto.data.repositories.WeatherRepository
import com.isen.mehto.data.repositories.impl.WeatherRepositoryImpl
import com.isen.mehto.weather.api.WeatherCall
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
                databaseModule,
                apiService,
            )
        }
    }
}

val repositoryModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    single<OfflineConfigRepository> { OfflineConfigRepository(get()) }
    single<OfflineFavoriteLocationRepository> { OfflineFavoriteLocationRepository(get()) }
}

val dataSourcesModule = module { }

val databaseModule = module {
    single { Room.databaseBuilder(get(), LocalDatabase::class.java, "LocalDatabase").build() }
    single { get<LocalDatabase>().configDAO() }
}

val apiService = module {
    single { WeatherCall.service }
}
