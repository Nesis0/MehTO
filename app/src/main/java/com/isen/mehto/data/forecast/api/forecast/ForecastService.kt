package com.isen.mehto.data.forecast.api.forecast

import com.isen.mehto.data.models.FiveDaysForecastResponse
import com.isen.mehto.data.models.Location
import com.isen.mehto.data.models.SingleDayForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY: String = "63b335fbcbab900e14a56296d96716b4"

interface ForecastService {
    @GET("data/2.5/weather")
    suspend fun getTodayWeather(@Query("lat") latitude: Double, @Query("lon") longitude: Double, @Query("appid") apiKey: String? = API_KEY): SingleDayForecastResponse

    @GET("/data/2.5/forecast")
    suspend fun getForecast(@Query("lat") latitude: Double, @Query("lon") longitude: Double, @Query("appid") apiKey: String? = API_KEY): FiveDaysForecastResponse

    @GET("geo/1.0/direct")
    suspend fun getLocationFromName(@Query("q") name: String, @Query("limit") limit: Int? = 5, @Query("appid") apiKey: String? = API_KEY): List<Location>
}