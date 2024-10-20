package com.isen.mehto.data.forecast.api.map

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


const val API_KEY: String = "63b335fbcbab900e14a56296d96716b4"
const val ZOOM_LEVEL: String = "3"
const val X_TILES: String = "3"
const val Y_TILES: String = "3"

interface MapService {
    @GET("map/precipitation_new/{z}/{x}/{y}.png")
    suspend fun getPrecipitationMap(@Path("z") zoomLevel: String? = ZOOM_LEVEL, @Path("x") xTiles: String? = X_TILES, @Path("y") yTiles: String? = Y_TILES, @Query("appid") apiKey: String? = API_KEY)

    @GET("map/temp_new/{z}/{x}/{y}.png")
    suspend fun getTemperaturesMap(@Path("z") zoomLevel: String? = ZOOM_LEVEL, @Path("x") xTiles: String? = X_TILES, @Path("y") yTiles: String? = Y_TILES, @Query("appid") apiKey: String? = API_KEY)

    @GET("map/pressure_new/{z}/{x}/{y}.png")
    suspend fun getPressureMap(@Path("z") zoomLevel: String? = ZOOM_LEVEL, @Path("x") xTiles: String? = X_TILES, @Path("y") yTiles: String? = Y_TILES, @Query("appid") apiKey: String? = API_KEY)

    @GET("map/wind_new/{z}/{x}/{y}.png")
    suspend fun getWindsMap(@Path("z") zoomLevel: String? = ZOOM_LEVEL, @Path("x") xTiles: String? = X_TILES, @Path("y") yTiles: String? = Y_TILES, @Query("appid") apiKey: String? = API_KEY)

    @GET("map/clouds_new/{z}/{x}/{y}.png")
    suspend fun getCloudMap(@Path("z") zoomLevel: String? = ZOOM_LEVEL, @Path("x") xTiles: String? = X_TILES, @Path("y") yTiles: String? = Y_TILES, @Query("appid") apiKey: String? = API_KEY)
}