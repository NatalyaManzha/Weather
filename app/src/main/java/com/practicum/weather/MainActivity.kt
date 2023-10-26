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
            val forecastOne = (forecastApp).forecastStorage.getOneDayForecast () // пока не используется (задел на будущее)

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


        class ForecastAdapter(private val forecastList: List<Data>) :
            RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.day_forecast, parent, false)
                return ForecastViewHolder(view)
            }

            override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
                holder.bind(forecastList[position])
            }

            override fun getItemCount() = forecastList.size

            class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                private val day: TextView
                private val temperature: TextView
                private val description: TextView
                private val icon: ImageView

                init {
                    day = itemView.findViewById(R.id.forecast_day)
                    temperature = itemView.findViewById(R.id.forecast_temperature)
                    description = itemView.findViewById(R.id.forecast_text)
                    icon = itemView.findViewById(R.id.forecast_icon)
                }

                fun bind(forecast: Data) {
                    day.text = forecast.day
                    temperature.text = forecast.temperature
                    description.text = forecast.description
                    icon.setImageResource(forecast.iconId)
                }
            }
        }
    }