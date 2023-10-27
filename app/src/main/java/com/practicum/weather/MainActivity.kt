package com.practicum.weather

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ForecastApp : Application() {
    lateinit var forecastStorage : ForecastStorage

    
    override fun onCreate() {
        super.onCreate()

        forecastStorage = ForecastStorage()
    }
}

    class MainActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            val forecastApp = (application as Context).applicationContext as ForecastApp
            (forecastApp).forecastStorage.init(forecastApp)
            val forecastList = (forecastApp).forecastStorage.getLongTermForecast()
            val forecastOne =
                (forecastApp).forecastStorage.getOneDayForecast() // пока не используется (задел на будущее)

            val astralButton = findViewById<ImageButton>(R.id.astral)
            astralButton.setOnClickListener {
                finish()
            }
            val astralText = findViewById<TextView>(R.id.astral_text)


            val recyclerView = findViewById<RecyclerView>(R.id.forecast)
            recyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = ForecastAdapter(forecastList)

            val bubenButton = findViewById<Button>(R.id.buben)
            bubenButton.setOnClickListener {
                bubenButton.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                astralButton.visibility = View.VISIBLE
                astralText.visibility = View.VISIBLE
            }
        }
    }