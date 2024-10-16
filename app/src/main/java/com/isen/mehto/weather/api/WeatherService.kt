package com.isen.mehto.weather.api

import retrofit2.http.GET
import retrofit2.http.Path

data class CityInfo(val weather: String, val temp: String)
data class Coordinates(val latitude: String, val longitude: String)
const val apiKey: String = "63b335fbcbab900e14a56296d96716b4"

interface WeatherService {
    @GET("data/2.5/weather?lat{latitude}&lon={longitude}&appid=$apiKey")
    suspend fun getTodayWeather(@Path("latitude") latitude: String, @Path("longitude") longitude: String): CityInfo

    @GET("geo/1.0/direct?q={city},{country}&limit=5&appid=$apiKey")
    suspend fun getCoordinatesFromCity(@Path("city") city: String, @Path("country") country: String): Coordinates
}