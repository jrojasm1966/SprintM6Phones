package com.example.phones.Modelo

import com.example.phones.Modelo.local.entities.PhonesDetailEntity
import com.example.phones.Modelo.local.entities.PhonesEntity
import com.example.phones.Modelo.remote.frominternet.PhonesDetail
import com.example.phones.Modelo.remote.frominternet.Phones

fun fromInternetPhonesEntity( phonesList: List<Phones>) :List<PhonesEntity>{
    return phonesList.map {
        PhonesEntity(
            id = it.id,
            name = it.name,
            price = it.price,
            image = it.image
        )
    }
}

fun fromInternetPhonesDetailEntity( phones: PhonesDetail) :PhonesDetailEntity{
    return PhonesDetailEntity(
        id = phones.id,
        name = phones.name,
        price = phones.price,
        image = phones.image,
        description = phones.description,
        lastPrice = phones.lastPrice,
        credit = phones.credit
    )
}
