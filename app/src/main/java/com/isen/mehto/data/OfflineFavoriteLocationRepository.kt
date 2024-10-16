package com.isen.mehto.data

import com.isen.mehto.data.entity.FavoriteLocation
import com.isen.mehto.data.entity.FavoriteLocationDAO

class OfflineFavoriteLocationRepository(private val cityDAO: FavoriteLocationDAO) : FavoriteLocationRepository {
    override suspend fun getAllCities(): List<FavoriteLocation> {
        return cityDAO.getAllCities()
    }

    override suspend fun insertCity(location: FavoriteLocation) {
        return cityDAO.insert(location)
    }

    override suspend fun getLocationInfo(displayName: String): FavoriteLocation? {
        return cityDAO.getLocationInfo(displayName)
    }

    override suspend fun updateCity(location: FavoriteLocation) {
        return cityDAO.update(location)
    }

    override suspend fun deleteCity(location: FavoriteLocation) {
        return cityDAO.delete(location)
    }

    override suspend fun clearCityList() {
        return cityDAO.clearCityList()
    }
}