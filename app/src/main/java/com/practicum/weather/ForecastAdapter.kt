package com.practicum.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

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
            description.text = forecast.description
            icon.setImageResource(forecast.iconId)
            val temperatureAttrs = setTemperatureTextAttributes(forecast.temperature)
            temperature.text = temperatureAttrs.first
            temperature.setTextColor(itemView.context.getColor(temperatureAttrs.second))
        }
    }
}
