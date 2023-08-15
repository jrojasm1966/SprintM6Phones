package com.example.phones.Modelo.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="phones_detail_table")
data class PhonesDetailEntity(
    @PrimaryKey
    val id : Int,
    val name: String,
    val price: Int,
    val image: String,
    val description: String,
    val lastPrice: Int,
    val credit: Boolean
)
