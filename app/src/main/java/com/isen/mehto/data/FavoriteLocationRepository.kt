package com.isen.mehto.data

import com.isen.mehto.data.entity.FavoriteLocation

interface FavoriteLocationRepository {
    suspend fun getAllCities(): List<FavoriteLocation>

    suspend fun insertCity(location: FavoriteLocation)

    suspend fun getLocationInfo(displayName: String): FavoriteLocation?

    suspend fun updateCity(location: FavoriteLocation)

    suspend fun deleteCity(location: FavoriteLocation)

    suspend fun clearCityList()
}