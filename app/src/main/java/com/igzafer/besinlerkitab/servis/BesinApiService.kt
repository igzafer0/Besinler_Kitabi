package com.igzafer.besinlerkitab.servis

import com.igzafer.besinlerkitab.model.BesinModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BesinApiService {
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(IBesinApi::class.java)

    fun getData() : Single<List<BesinModel>>{
        return api.getBesin()
    }
}