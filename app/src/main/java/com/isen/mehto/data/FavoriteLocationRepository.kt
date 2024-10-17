package com.isen.mehto.data

import com.isen.mehto.data.entity.FavoriteLocation

interface FavoriteLocationRepository {
    suspend fun getAllLocations(): List<FavoriteLocation>

    suspend fun insertLocation(location: FavoriteLocation)

    suspend fun getLocationInfo(displayName: String): FavoriteLocation?

    suspend fun updateLocation(location: FavoriteLocation)

    suspend fun deleteLocation(location: FavoriteLocation)

    suspend fun clearLocationList()
}