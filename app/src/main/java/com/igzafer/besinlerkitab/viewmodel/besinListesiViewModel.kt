package com.igzafer.besinlerkitab.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.igzafer.besinlerkitab.model.besinModel
import com.igzafer.besinlerkitab.model.besinState
import com.igzafer.besinlerkitab.servis.BesinApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class besinListesiViewModel : ViewModel() {

    val besinler = MutableLiveData<List<besinModel>>()
    val besinDurum = MutableLiveData<besinState>()

    private val besinApiServis = BesinApiService()
    private val disposable = CompositeDisposable()


    fun refreshData() {
        verileriInternettenAl()

    }

    private fun verileriInternettenAl() {
        besinDurum.value = besinState.YUKLENIYOR
        disposable.add(
            besinApiServis.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<besinModel>>() {
                    override fun onSuccess(p0: List<besinModel>) {
                        besinler.value = p0
                        besinDurum.value = besinState.BASARILI
                    }

                    override fun onError(p0: Throwable) {
                        besinDurum.value = besinState.HATA
                        p0.printStackTrace()

                    }

                })

        )
    }
}