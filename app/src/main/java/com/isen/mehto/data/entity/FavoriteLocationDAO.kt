package com.isen.mehto.data.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FavoriteLocationDAO {
    @Query("SELECT * FROM favoritelocation ORDER BY preference_index")
    suspend fun getAllLocations(): List<FavoriteLocation>

    @Insert
    suspend fun insert(location: FavoriteLocation)

    @Query("SELECT * FROM favoritelocation WHERE display_name = :displayName")
    suspend fun read(displayName: String): FavoriteLocation?

    @Query("SELECT * FROM favoritelocation WHERE preference_index = :preferenceIndex")
    suspend fun readByIndex(preferenceIndex: String): FavoriteLocation

    @Update
    suspend fun update(location: FavoriteLocation)

    @Delete
    suspend fun delete(location: FavoriteLocation)

    @Query("DELETE FROM favoritelocation")
    suspend fun clearLocationList()
}