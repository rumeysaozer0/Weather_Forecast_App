package com.rumeysaozer.havadurumuuygulamas.servis

import com.rumeysaozer.havadurumuuygulamas.model.HavaDurumu
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface HavaApi {
   //http://api.openweathermap.org/data/2.5/weather?q=bingöl&APPID=04a42b96398abc8e4183798ed22f9485

    @GET("data/2.5/weather?&units=metric&appid=f8b76ecbf5103ecda17e1b745d97391a")
    fun getData(
        @Query("q") sehirAdi: String
    ): Single<HavaDurumu>

}
