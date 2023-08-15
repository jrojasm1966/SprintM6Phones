package com.example.phones.Modelo.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="phones_list_table")
data class PhonesEntity(
    @PrimaryKey
    val id : Int,
    val name: String,
    val price: Int,
    val image: String
)
