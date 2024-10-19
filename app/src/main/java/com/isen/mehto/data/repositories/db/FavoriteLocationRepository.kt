package com.isen.mehto.data.repositories.db

import com.isen.mehto.data.entity.FavoriteLocation

interface FavoriteLocationRepository {
    suspend fun getAllLocations(): List<FavoriteLocation>

    suspend fun insert(location: FavoriteLocation)

    suspend fun readByLocationName(displayName: String): FavoriteLocation?

    suspend fun readByIndex(preferenceIndex: String): FavoriteLocation

    suspend fun update(location: FavoriteLocation)

    suspend fun delete(location: FavoriteLocation)

    suspend fun clearLocationList()
}