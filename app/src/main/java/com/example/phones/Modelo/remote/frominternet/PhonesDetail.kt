package com.example.phones.Modelo.remote.frominternet

data class PhonesDetail (
    val id : Int,
    val name: String,
    val price: Int,
    val image: String,
    val description: String,
    val lastPrice: Int,
    val credit: Boolean
)