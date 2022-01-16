package com.igzafer.besinlerkitab.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.igzafer.besinlerkitab.model.BesinModel
import com.igzafer.besinlerkitab.model.besinState
import com.igzafer.besinlerkitab.servis.BesinApiService
import com.igzafer.besinlerkitab.servis.besinDatabase
import com.igzafer.besinlerkitab.utils.OzelSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class besinListesiViewModel(application: Application) : baseViewModel(application) {

    val besinler = MutableLiveData<List<BesinModel>>()
    val besinDurum = MutableLiveData<besinState>()
    private val besinApiServis = BesinApiService()
    private val disposable = CompositeDisposable()
    private val GUNCELLEME = 10 * 60 * 1000 * 1000 * 1000L
    private val ozelSharedPreferences = OzelSharedPreferences(getApplication())

    fun refreshData() {
        val kaydedilmeZamani = ozelSharedPreferences.zamaniAl()
        if (kaydedilmeZamani != null && kaydedilmeZamani != 0L && System.nanoTime() - kaydedilmeZamani < GUNCELLEME) {
            verileriSQLitetanAL()
        } else {
            verileriInternettenAl()
        }
    }
    fun refreshFromInternet(){
        verileriInternettenAl()
    }
    private fun verileriSQLitetanAL() {
        besinDurum.value = besinState.YUKLENIYOR
        launch {
            val besinListesi = besinDatabase(getApplication()).besinDao().getAllBesin()
            besinleriGoster(besinListesi)
            Toast.makeText(getApplication(), "veriler sqlden", Toast.LENGTH_LONG).show()
        }
    }

    private fun verileriInternettenAl() {
        besinDurum.value = besinState.YUKLENIYOR
        disposable.add(
            besinApiServis.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<BesinModel>>() {
                    override fun onSuccess(p0: List<BesinModel>) {
                        Toast.makeText(getApplication(), "veriler internetten", Toast.LENGTH_LONG)
                            .show()
                        sqliteSakla(p0)
                    }

                    override fun onError(p0: Throwable) {
                        besinDurum.value = besinState.HATA
                        p0.printStackTrace()

                    }

                })
        )
    }

    private fun besinleriGoster(p0: List<BesinModel>) {
        besinler.value = p0
        besinDurum.value = besinState.BASARILI
    }

    private fun sqliteSakla(besinListesi: List<BesinModel>) {
        launch {
            val dao = besinDatabase(getApplication()).besinDao()
            dao.deleteAllBesin()
            val uuidList = dao.insertAll(*besinListesi.toTypedArray())
            var i = 0
            while (i < uuidList.size) {
                besinListesi[i].uuid = uuidList[i].toInt()
                i += 1
            }
            besinleriGoster(besinListesi)
        }
        ozelSharedPreferences.zamaniKaydet(System.nanoTime())
    }
}