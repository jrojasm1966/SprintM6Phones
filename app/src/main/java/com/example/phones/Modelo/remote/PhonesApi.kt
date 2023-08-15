package com.example.phones.Modelo.remote

import com.example.phones.Modelo.remote.frominternet.PhonesDetail
import com.example.phones.Modelo.remote.frominternet.Phones
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhonesApi {

    @GET("products")
    suspend fun fetchCourseList(): Response<List<Phones>>

    @GET("details/{id}")
    suspend fun fetchCourseDetail(@Path("id") id:Int) : Response<PhonesDetail>
}