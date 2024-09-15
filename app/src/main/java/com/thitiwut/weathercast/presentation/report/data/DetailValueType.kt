package com.thitiwut.weathercast.presentation.report.data

import com.thitiwut.weathercast.R

enum class DetailValueType(val title:String,val icon:Int,val unit:String) {
    PRESSURE("Pressure",R.drawable.ic_pressure_24,"hPa"),
    HUMIDITY("Humidity",R.drawable.ic_humidity_24,"%"),
    VISIBILITY("Visibility",R.drawable.ic_visibility_24,"km"),
    WIND_SPEED("Wind Speed",R.drawable.ic_wind_speed_24,"m/s"),
    RAIN("Rain", R.drawable.ic_outline_rainy_24,"mm"),
    SNOW("Snow",R.drawable.ic_snowing_24,"mm"),
    SUNRISE("Sunrise",0,""),
    SUNSET("Sunset",0,"")
}