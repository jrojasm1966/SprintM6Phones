package com.example.phones.Modelo.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.phones.Modelo.local.PhonesDao
import com.example.phones.Modelo.local.entities.PhonesDetailEntity
import com.example.phones.Modelo.local.entities.PhonesEntity

@Database(entities = [PhonesEntity:: class,PhonesDetailEntity::class], version = 1,
exportSchema = false)
abstract class PhonesDataBase: RoomDatabase() {

    // referencia del dao
    abstract fun getPhonesDao(): PhonesDao

    companion object{

        @Volatile
        private var
                INSTANCE : PhonesDataBase? = null
        fun getDataBase(context: Context) : PhonesDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhonesDataBase::class.java, "Phones")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}