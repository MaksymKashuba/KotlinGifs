package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

const val BASE_URL = "https://api.giphy.com/v1/"
const val TAG = "Main"
class Main: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val gifs = mutableListOf<DataObject>()
        val adapter = GifsAdapter(this, gifs)

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retroService = retrofit.create(DataService::class.java)
        retroService.getGifs().enqueue(object : Callback<DataResult?> {
            override fun onResponse(call: Call<DataResult?>, response: Response<DataResult?>) {
                val body = response.body()
                if (body == null) {
                    Log.d(TAG, "onResponse: No response...")
                }
                gifs.addAll(body!!.res)
                adapter.notifyDataSetChanged()
            }
        })
    }
}