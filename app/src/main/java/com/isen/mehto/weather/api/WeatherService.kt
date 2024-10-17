package com.isen.mehto.weather.api

import com.isen.mehto.data.models.Position
import com.isen.mehto.data.models.Weather
import retrofit2.http.GET
import retrofit2.http.Path

data class CityInfo(val weather: String, val temp: String)

const val API_KEY: String = "63b335fbcbab900e14a56296d96716b4"

interface WeatherService {
    @GET("data/2.5/weather?lat={latitude}&lon={longitude}&appid=$API_KEY")
    suspend fun getTodayWeather(@Path("latitude") latitude: Float, @Path("longitude") longitude: Float): Weather

    @GET("/data/2.5/forecast?lat={latitude}&lon={longitude}&appid=$API_KEY")
    suspend fun getForecast(@Path("latitude") latitude: Float, @Path("longitude") longitude: Float): Weather

    @GET("geo/1.0/direct?q={city}&limit=5&appid=$API_KEY")
    suspend fun getCoordinatesFromCity(@Path("city") city: String): Position
}