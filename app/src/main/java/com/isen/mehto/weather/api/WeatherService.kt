package com.isen.mehto.weather.api

import com.isen.mehto.data.models.Position
import retrofit2.http.GET
import retrofit2.http.Path

data class CityInfo(val weather: String, val temp: String)

const val API_KEY: String = "63b335fbcbab900e14a56296d96716b4"

interface WeatherService {
    @GET("data/2.5/weather?lat{latitude}&lon={longitude}&appid=$API_KEY")
    suspend fun getTodayWeather(@Path("latitude") latitude: String, @Path("longitude") longitude: String): CityInfo

    @GET("geo/1.0/direct?q={city},{country}&limit=5&appid=$API_KEY")
    suspend fun getCoordinatesFromCity(@Path("city") city: String, @Path("country") country: String): Position
}