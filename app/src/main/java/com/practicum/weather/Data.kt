package com.practicum.weather
import android.app.Application
import androidx.annotation.DrawableRes


data class Data(
    val day: String,
    val temperature: String,
    val description: String,
    @DrawableRes
    val iconId: Int
)

class ForecastStorage () {
    lateinit private var forecasts: List<Data>

    fun init(context: Application) {
        val forecastData =context.assets.open("forecasts.txt").bufferedReader().use { it.readText() }.split("\n").map { it.split("%") }
        forecasts = forecastData.map { (day, temperature, iconName, description) ->
            val iconId = context.resources.getIdentifier(
                iconName,
                "drawable",
                context.packageName
            )
            Data(day, temperature, description, iconId)
        }
    }

    fun getLongTermForecast() = forecasts
    fun getOneDayForecast() = forecasts.shuffled().first()
}