package com.rumeysaozer.havadurumuuygulamas.servis

import com.rumeysaozer.havadurumuuygulamas.model.HavaDurumu
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class HavaApiService {
    //https://api.openweathermap.org/data/2.5/weather?q=ankara&appid=f8b76ecbf5103ecda17e1b745d97391a
    private val BASE_URL = "https://api.openweathermap.org/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(HavaApi::class.java)

    fun getDataService(sehirAdi: String): Single<HavaDurumu> {
        return api.getData(sehirAdi)
    }

}