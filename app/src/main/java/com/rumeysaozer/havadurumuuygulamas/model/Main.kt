package com.rumeysaozer.havadurumuuygulamas.model


import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("sea_level")
    val seaLevel: Int,
    val temp: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("grnd_level")
    val grndLevel: Int,
    val humidity: Int,

)