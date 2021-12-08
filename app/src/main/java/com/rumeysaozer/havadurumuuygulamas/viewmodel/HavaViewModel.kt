package com.rumeysaozer.havadurumuuygulamas.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rumeysaozer.havadurumuuygulamas.model.HavaDurumu
import com.rumeysaozer.havadurumuuygulamas.servis.HavaApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers



class HavaViewModel : ViewModel() {

    private val weatherApiService = HavaApiService()
    private val disposable = CompositeDisposable()

    val veri = MutableLiveData<HavaDurumu>()
    val hata = MutableLiveData<Boolean>()
    val yukleniyor = MutableLiveData<Boolean>()

    fun refreshData(sehirAdi: String) {
        getDataFromAPI(sehirAdi)
    }

    private fun getDataFromAPI(sehirAdi: String) {

        yukleniyor.value = true
        disposable.add(
            weatherApiService.getDataService(sehirAdi)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<HavaDurumu>() {

                    override fun onSuccess(t: HavaDurumu) {
                        veri.value = t
                        hata.value = false
                        yukleniyor.value = false

                    }

                    override fun onError(e: Throwable) {
                        hata.value = true
                        yukleniyor.value = false

                    }

                })
        )

    }

}