package data.entity

import data.CityRepository

class OfflineCityRepository(private val cityDAO: CityDAO) : CityRepository {
    override suspend fun getAllCities(): List<City> {
        return cityDAO.getAllCities()
    }

    override suspend fun insertCity(city: City) {
        return cityDAO.insert(city)
    }

    override suspend fun getCityInfo(cityName: String): City? {
        return cityDAO.getCityInfo(cityName)
    }

    override suspend fun updateCity(city: City) {
        return cityDAO.update(city)
    }

    override suspend fun deleteCity(city: City) {
        return cityDAO.delete(city)
    }

    override suspend fun clearCityList() {
        return cityDAO.clearCityList()
    }
}