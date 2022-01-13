package com.igzafer.besinlerkitab.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.igzafer.besinlerkitab.model.besinModel

class besinDetayiViewModel : ViewModel() {

    val besinLiveData = MutableLiveData<besinModel>()

    fun roomVerisiniAl() {
        val armut = besinModel(
            "Armut",
            "1000",
            "0",
            "1",
            "0",
            "https://seyler.ekstat.com/img/max/800/J/JS0yLXdQ9n46fHNi-636190377994184197.jpg"
        )
        besinLiveData.value = armut
    }
}