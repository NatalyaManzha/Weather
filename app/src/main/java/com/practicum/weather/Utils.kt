package com.practicum.weather

import kotlin.math.absoluteValue

fun setTemperatureTextAttributes(str : String): Pair<String, Int> {
    var text = ""
    val temp = str.toInt()
    if (temp>=0) {
        text = "+ $str °C"
    } else {
        text = "- ${str.toInt().absoluteValue}°C"
    }
    val tempColor  = when {
        temp < -30 -> R.color.blue_cold
        temp in -30..-10 -> R.color.blue
        temp in -10..0 -> R.color.green_blue
        temp in 0..10 -> R.color.green_worm
        temp in 10..19 -> R.color.green_yellow
        else -> R.color.yellow_worm
    }
    return Pair (text, tempColor)
}