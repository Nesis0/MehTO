package com.isen.mehto.data.repositories.api.map.impl

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.isen.mehto.data.forecast.api.map.MapService
import com.isen.mehto.data.repositories.api.map.MapRepository

class MapRepositoryImpl(private val mapService: MapService): MapRepository {
    override suspend fun getPrecipitationMap(): Bitmap {
        return BitmapFactory.decodeStream(mapService.getPrecipitationMap().byteStream())
    }

    override suspend fun getTemperaturesMap(): Bitmap {
        return BitmapFactory.decodeStream(mapService.getTemperaturesMap().byteStream())
    }

    override suspend fun getPressureMap(): Bitmap {
        return BitmapFactory.decodeStream(mapService.getPressureMap().byteStream())
    }

    override suspend fun getWindsMap(): Bitmap {
        return BitmapFactory.decodeStream(mapService.getWindsMap().byteStream())
    }

    override suspend fun getCloudMap(): Bitmap {
        return BitmapFactory.decodeStream(mapService.getCloudMap().byteStream())
    }
}