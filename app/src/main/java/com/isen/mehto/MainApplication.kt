package com.isen.mehto

import android.app.Application
import androidx.room.Room
import com.isen.mehto.data.repositories.db.impl.OfflineConfigRepository
import com.isen.mehto.data.repositories.db.impl.OfflineFavoriteLocationRepository
import com.isen.mehto.data.db.LocalDatabase
import com.isen.mehto.data.repositories.api.ForecastRepository
import com.isen.mehto.data.repositories.api.impl.ForecastRepositoryImpl
import com.isen.mehto.data.forecast.api.ForecastApi
import com.isen.mehto.data.forecast.api.WeatherServiceImpl
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
    single<ForecastRepository> { ForecastRepositoryImpl(get()) }
    single<OfflineConfigRepository> { OfflineConfigRepository(get()) }
    single<OfflineFavoriteLocationRepository> { OfflineFavoriteLocationRepository(get()) }
}

val dataSourcesModule = module { }

val databaseModule = module {
    single { Room.databaseBuilder(get(), LocalDatabase::class.java, "LocalDatabase").build() }
    single { get<LocalDatabase>().configDAO() }
    single { get<LocalDatabase>().FavoriteLocationDAO() }
}

val apiService = module {
    single { ForecastApi.service }
    single { WeatherServiceImpl() }
}
