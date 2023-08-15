package com.example.phones.Modelo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.phones.Modelo.local.PhonesDao
import com.example.phones.Modelo.local.entities.PhonesDetailEntity
import com.example.phones.Modelo.remote.RetrofitClient

class PhonesRepository(private val phonesDao: PhonesDao) {

    // retrofit Cliente
    private val networkService = RetrofitClient.retrofitInstance()

    // dao listado
     val phonesListLiveData = phonesDao.getAllPhones()

    // un elemento
    val phoneDetailLiveData= MutableLiveData<PhonesDetailEntity>()

    suspend fun fetchPhone(){
        val service = kotlin.runCatching { networkService.fetchCourseList() }

        service.onSuccess {
            when (it.code()){
                in 200..299 ->it.body()?.let {
                    Log.d("Phones",it.toString())
                  phonesDao.insertAllPhones(fromInternetPhonesEntity(it))
                }
                else-> Log.d("Repo","${it.code()}-${it.errorBody()}")
            }
            service.onFailure {
                Log.e("Error", "${it.message}")
            }
        }
    }

    suspend fun fetchPhoneDetail(id: Int): PhonesDetailEntity?{
        val service = kotlin.runCatching { networkService.fetchCourseDetail(id) }
        return service.getOrNull()?.body()?.let { phoneDetail ->
            // guardo los datos que viene del mapper y luego se los paso a dao directo
            val phoneDetailEntity = fromInternetPhonesDetailEntity(phoneDetail)
            //inserto los detalles de los phones DEL REPOSITORIO
            phonesDao.insertPhoneDetail(phoneDetailEntity)
            phoneDetailEntity
        }
    }
}