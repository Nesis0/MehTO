package com.isen.mehto.data.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FavoriteLocationDAO {
    @Query("SELECT * FROM favoritelocation")
    suspend fun getAllLocations(): List<FavoriteLocation>

    @Insert
    suspend fun insert(location: FavoriteLocation)

    @Query("SELECT * FROM favoritelocation WHERE display_name = :displayName")
    suspend fun getLocationInfo(displayName: String): FavoriteLocation?

    @Update
    suspend fun update(location: FavoriteLocation)

    @Delete
    suspend fun delete(location: FavoriteLocation)

    @Query("DELETE FROM favoritelocation")
    suspend fun clearLocationList()
}