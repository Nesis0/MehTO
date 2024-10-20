package com.isen.mehto.data.repositories.api.map

import android.graphics.Bitmap

interface MapRepository {
    suspend fun getPrecipitationMap(): Bitmap
    suspend fun getTemperaturesMap(): Bitmap
    suspend fun getPressureMap(): Bitmap
    suspend fun getWindsMap(): Bitmap
    suspend fun getCloudMap(): Bitmap
}