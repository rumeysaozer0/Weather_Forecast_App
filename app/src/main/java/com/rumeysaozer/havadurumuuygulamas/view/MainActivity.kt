package com.rumeysaozer.havadurumuuygulamas.view


import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.rumeysaozer.havadurumuuygulamas.R
import com.rumeysaozer.havadurumuuygulamas.viewmodel.HavaViewModel
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    private lateinit var viewmodel: HavaViewModel
    private lateinit var GET: SharedPreferences
    private lateinit var SET: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GET = getSharedPreferences(packageName, MODE_PRIVATE)
        SET = GET.edit()

        viewmodel = ViewModelProviders.of(this).get(HavaViewModel::class.java)

        var sehir = GET.getString("sehirAdi", "ankara")?.lowercase()
        sehirAdiGir.setText(sehir)
        viewmodel.refreshData(sehir!!)

        observeLiveData()

        srl.setOnRefreshListener {
            linearLayout.visibility = View.GONE
            hata.visibility = View.GONE
            yukleniyor.visibility = View.GONE

            var sehirAdi = GET.getString("sehirAdi", sehir)?.lowercase()
            sehirAdiGir.setText(sehirAdi)
            viewmodel.refreshData(sehirAdi!!)
            srl.isRefreshing = false
        }

        sehirAra.setOnClickListener {
            val sehirAdi = sehirAdiGir.text.toString()
            SET.putString("cityName", sehirAdi)
            SET.apply()
            viewmodel.refreshData(sehirAdi)
            observeLiveData()

        }

    }



    private fun observeLiveData() {

        viewmodel.veri.observe(this, Observer { data ->
            data?.let {
                linearLayout.visibility = View.VISIBLE

                ulkeKodu.text = data.sys.country
                sehirAdi.text = data.name

                Glide.with(this)
                    .load("https://openweathermap.org/img/wn/" + data.weather.get(0).icon + "@2x.png")
                    .into(icon)



                derece.text = data.main.temp.toInt().toString() + "°C"

                nem.text = data.main.humidity.toString() + "%"
                ruzgar.text = data.wind.speed.toString()
                max.text = data.main.tempMax.toInt().toString()  + "°C"
                min.text = data.main.tempMin.toInt().toString()  + "°C"



            }
        })

        viewmodel.hata.observe(this, Observer { havaHata ->
            havaHata?.let {
                if (havaHata) {
                    hata.visibility = View.VISIBLE
                    yukleniyor.visibility = View.GONE
                    linearLayout.visibility = View.GONE
                } else {
                    hata.visibility = View.GONE
                }
            }
        })

        viewmodel.yukleniyor.observe(this, Observer { yukle ->
            yukle?.let {
                if (yukle) {
                    yukleniyor.visibility = View.VISIBLE
                    hata.visibility = View.GONE
                    linearLayout.visibility = View.GONE
                } else {
                    yukleniyor.visibility = View.GONE
                }
            }
        })

    }
}