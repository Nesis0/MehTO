package com.isen.mehto.weather.api

import com.isen.mehto.data.models.Position
import com.isen.mehto.data.models.Weather
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY: String = "63b335fbcbab900e14a56296d96716b4"

interface WeatherService {
    @GET("data/2.5/weather")
    suspend fun getTodayWeather(@Query("lat") latitude: Float, @Query("lon") longitude: Float, @Query("appid") apiKey: String? = API_KEY): Weather

    @GET("/data/2.5/forecast")
    suspend fun getForecast(@Query("lat") latitude: Float, @Query("lon") longitude: Float, @Query("appid") apiKey: String? = API_KEY): Weather

    @GET("geo/1.0/direct")
    suspend fun getCoordinatesFromCity(@Query("q") city: String, @Query("appid") apiKey: String? = API_KEY): Position
}