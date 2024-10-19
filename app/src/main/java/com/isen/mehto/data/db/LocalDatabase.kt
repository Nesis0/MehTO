package com.isen.mehto.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.isen.mehto.data.entity.FavoriteLocation
import com.isen.mehto.data.entity.FavoriteLocationDAO
import com.isen.mehto.data.entity.Config
import com.isen.mehto.data.entity.ConfigDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(version = 1, entities = [Config::class, FavoriteLocation::class], exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
     abstract fun configDAO(): ConfigDAO

     abstract fun FavoriteLocationDAO(): FavoriteLocationDAO

     companion object {
          @Volatile
          private var Instance: LocalDatabase? = null

          fun getDatabase(context: Context): LocalDatabase {
               return Instance ?: synchronized(this) {
                    Room.databaseBuilder(context, LocalDatabase::class.java,"MehTODatabase")
                         .addCallback(object : RoomDatabase.Callback() {
                              override fun onCreate(db: SupportSQLiteDatabase) {
                                   super.onCreate(db)
                                   populateDatabase(getDatabase(context).configDAO())
                              }
                         })
                         .build()
                         .also { Instance = it}
               }
          }

          fun populateDatabase(configDAO: ConfigDAO) {
               CoroutineScope(Dispatchers.IO).launch {
                    configDAO.insert(Config("unit", "Celsius"))
                    configDAO.insert(Config("Language", "FR"))
                    configDAO.insert(Config("Location", "true"))
               }
          }
     }

}