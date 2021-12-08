package com.rumeysaozer.havadurumuuygulamas.model


data class HavaDurumu(
    val clouds: Bulut,
    val main: Main,
    val name: String,
    val sys: Sys,
    val weather: List<Hava>,
    val wind: Ruzgar
)