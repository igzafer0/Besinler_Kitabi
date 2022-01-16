package com.igzafer.besinlerkitab.servis

import com.igzafer.besinlerkitab.model.BesinModel
import io.reactivex.Single
import retrofit2.http.GET

interface IBesinApi {

    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    fun getBesin():Single<List<BesinModel>>
}