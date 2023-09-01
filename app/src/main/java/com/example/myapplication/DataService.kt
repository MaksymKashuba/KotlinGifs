package com.example.myapplication

import retrofit2.http.GET

interface DataService {

    @GET("gifs/trending?api_key=jYob3s7Dpt3Wg8q2DwEiPdyrBJDfYGnc")
    fun getGifs(): retrofit2.Call<DataResult>
}