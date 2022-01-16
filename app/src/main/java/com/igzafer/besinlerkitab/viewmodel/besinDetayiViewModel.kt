package com.igzafer.besinlerkitab.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.igzafer.besinlerkitab.model.BesinModel
import com.igzafer.besinlerkitab.servis.besinDatabase
import kotlinx.coroutines.launch

class  besinDetayiViewModel(application: Application) : baseViewModel(application) {

    val besinLiveData = MutableLiveData<BesinModel>()

    fun roomVerisiniAl(id: Int) {
        launch {
            val dao = besinDatabase(getApplication()).besinDao()
            val besin = dao.getSingleBesin(id)
            besinLiveData.value=besin
        }
    }
}