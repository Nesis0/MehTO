package com.isen.mehto.data

import com.isen.mehto.data.entity.FavoriteLocation
import com.isen.mehto.data.entity.FavoriteLocationDAO

class OfflineFavoriteLocationRepository(private val cityDAO: FavoriteLocationDAO) : FavoriteLocationRepository {
    override suspend fun getAllLocations(): List<FavoriteLocation> {
        return cityDAO.getAllLocations()
    }

    override suspend fun insertLocation(location: FavoriteLocation) {
        return cityDAO.insert(location)
    }

    override suspend fun getLocationInfo(displayName: String): FavoriteLocation? {
        return cityDAO.getLocationInfo(displayName)
    }

    override suspend fun updateLocation(location: FavoriteLocation) {
        return cityDAO.update(location)
    }

    override suspend fun deleteLocation(location: FavoriteLocation) {
        return cityDAO.delete(location)
    }

    override suspend fun clearLocationList() {
        return cityDAO.clearLocationList()
    }
}