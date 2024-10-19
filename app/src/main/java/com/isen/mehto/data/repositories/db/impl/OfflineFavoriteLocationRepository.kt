package com.isen.mehto.data.repositories.db.impl

import com.isen.mehto.data.entity.FavoriteLocation
import com.isen.mehto.data.entity.FavoriteLocationDAO
import com.isen.mehto.data.repositories.db.FavoriteLocationRepository

class OfflineFavoriteLocationRepository(private val cityDAO: FavoriteLocationDAO) :
    FavoriteLocationRepository {
    override suspend fun getAllLocations(): List<FavoriteLocation> {
        return cityDAO.getAllLocations()
    }

    override suspend fun insert(location: FavoriteLocation) {
        return cityDAO.insert(location)
    }

    override suspend fun read(displayName: String): FavoriteLocation? {
        return cityDAO.read(displayName)
    }

    override suspend fun readByIndex(preferenceIndex: String): FavoriteLocation{
        return cityDAO.readByIndex(preferenceIndex)
    }

    override suspend fun update(location: FavoriteLocation) {
        return cityDAO.update(location)
    }

    override suspend fun delete(location: FavoriteLocation) {
        return cityDAO.delete(location)
    }

    override suspend fun clearLocationList() {
        return cityDAO.clearLocationList()
    }
}