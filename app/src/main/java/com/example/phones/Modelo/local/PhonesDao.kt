package com.example.phones.Modelo.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.phones.Modelo.local.entities.PhonesDetailEntity
import com.example.phones.Modelo.local.entities.PhonesEntity

@Dao
interface PhonesDao {

    // insertar lista de Phones
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPhones(listPhones: List<PhonesEntity>)

    // seleccionar Listado de Phones
    @Query("SELECT * FROM phones_list_table ORDER BY id ASC")
    fun getAllPhones(): LiveData<List<PhonesEntity>>

    // inserta de a 1 course
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoneDetail(phone: PhonesDetailEntity)

    @Query("SELECT * FROM phones_detail_table WHERE id=:id")
    fun getPhoneDetailById(id: Int): LiveData<PhonesDetailEntity>

}