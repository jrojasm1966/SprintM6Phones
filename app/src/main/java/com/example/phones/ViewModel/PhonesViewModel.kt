package com.example.phones.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.phones.Modelo.PhonesRepository
import com.example.phones.Modelo.local.database.PhonesDataBase
import com.example.phones.Modelo.local.entities.PhonesDetailEntity
import com.example.phones.Modelo.local.entities.PhonesEntity
import kotlinx.coroutines.launch
import java.sql.RowId

class PhonesViewModel(application: Application) : AndroidViewModel(application){

    // conexión repository

    private val repository : PhonesRepository

    // entidades
    private val phoneDetailLiveData = MutableLiveData<PhonesDetailEntity>()

    // para seleccionar
    private var idSelected : String="-1"

    init{
        // tiene la instancia de la bd el dao y lo entregamos el repository
        val bd = PhonesDataBase.getDataBase(application)
        val PhonesDao = bd.getPhonesDao()

        repository = PhonesRepository(PhonesDao)

       // llamo al método del repository
       viewModelScope.launch {
           repository.fetchPhone()
       }
    }

    // listado de los elementos
    fun getPhonesList(): LiveData<List<PhonesEntity>> = repository.phonesListLiveData

    // para obtener un phone por id desde lo que se selecciono
    fun getPhoneDetail(): LiveData<PhonesDetailEntity> = phoneDetailLiveData

    // desde el segundo fragmento le paso la seleccion
    fun getPhoneDetailByIdFromInternet(id: Int) = viewModelScope.launch {

        val phoneDetail = repository.fetchPhoneDetail(id)
        phoneDetail?.let {
            phoneDetailLiveData.postValue(it)
        }
    }
}