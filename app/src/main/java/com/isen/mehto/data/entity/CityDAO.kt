package com.isen.mehto.data.entity

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface CityDAO {
    @Query("SELECT * FROM city")
    suspend fun getAllCities(): List<City>

    @Insert
    suspend fun insert(city: City)

    @Query("SELECT * FROM city WHERE name=:cityName")
    suspend fun getCityInfo(cityName: String): City?

    @Update
    suspend fun update(city: City)

    @Delete
    suspend fun delete(city: City)

    @Query("DELETE FROM city")
    suspend fun clearCityList()


}