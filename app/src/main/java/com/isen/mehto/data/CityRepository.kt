package com.isen.mehto.data

import com.isen.mehto.data.entity.City

interface CityRepository {
    suspend fun getAllCities(): List<City>

    suspend fun insertCity(city: City)

    suspend fun getCityInfo(cityName: String): City?

    suspend fun updateCity(city: City)

    suspend fun deleteCity(city: City)

    suspend fun clearCityList()

}