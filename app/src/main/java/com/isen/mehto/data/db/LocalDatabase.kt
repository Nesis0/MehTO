package com.isen.mehto.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.isen.mehto.data.entity.City
import com.isen.mehto.data.entity.CityDAO
import com.isen.mehto.data.entity.Config
import com.isen.mehto.data.entity.ConfigDAO

@Database(version = 1, entities = [Config::class, City::class])
abstract class LocalDatabase : RoomDatabase() {
     abstract fun configDAO(): ConfigDAO

     abstract fun cityDAO(): CityDAO



     companion object {
          @Volatile
          private var Instance: LocalDatabase? = null

          fun getDatabase(context: Context): LocalDatabase {
               return Instance ?: synchronized(this) {
                    Room.databaseBuilder(context, LocalDatabase::class.java,"LocalDatabase")
                         .build()
                         .also { Instance = it}
               }
          }
     }
}