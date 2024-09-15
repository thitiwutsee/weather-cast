package com.thitiwut.weathercast.data.remote

import com.google.gson.annotations.SerializedName
import com.thitiwut.weathercast.presentation.report.data.WeatherDetailInfo
import com.thitiwut.weathercast.presentation.report.data.WeatherMainInfo
import com.thitiwut.weathercast.util.convertUnixToHHmm

data class WeatherResponse(
    @SerializedName("coord")
    val location: Location,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("base")
    val base: String,
    @SerializedName("main")
    val main: Main,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("wind")
    val wind: Wind,
    @SerializedName("rain")
    val rain: Hour?=null,
    @SerializedName("snow")
    val snow: Hour?=null,
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("cod")
    val code: Int,
    @SerializedName("message")
    val message: String? = null
){
    fun mapMainInfo(): WeatherMainInfo {
        return WeatherMainInfo(
            main = weather[0].main,
            description = weather[0].description,
            iconUrl = "https://openweathermap.org/img/wn/${weather[0].icon}@2x.png",
            temp = main.temp,
            feelsLike = main.feelsLike,
            tempMin = main.tempMin,
            tempMax = main.tempMax
        )
    }

    fun mapDetailInfo(): WeatherDetailInfo {
        return WeatherDetailInfo(
            pressure = main.pressure,
            humidity = main.humidity,
            visibility = (visibility/1000),
            windSpeed = wind.speed,
            rain = rain,
            snow = snow,
            sunrise = sys.sunrise.convertUnixToHHmm(),
            sunset = sys.sunset.convertUnixToHHmm()
        )
    }
}

val dummyData = WeatherResponse(
    location = Location(0.0,0.0),
    weather = listOf(Weather(0,"Rain","have rain","")),
    base = "",
    main = Main(0.0,0.0,0.0,0.0,0,0),
    visibility = 0,
    wind = Wind(0.0,0,0.0),
    rain = Hour(0.0,0.0),
    snow = Hour(0.0,0.0),
    clouds = Clouds(0),
    dt = 0,
    sys = Sys(0,0,"",0,0),
    timezone = 0,
    id = 0,
    name = "",
    code = 0,
    message = ""
)

data class Location(
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("lat")
    val lat: Double
)

data class Weather(
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String
)

data class Main(
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("humidity")
    val humidity: Int
)
data class Wind(
    @SerializedName("speed")
    val speed: Double,
    @SerializedName("deg")
    val deg: Int,
    @SerializedName("gust")
    val gust: Double
)

data class Hour(
    @SerializedName("1h")
    val oneHour: Double,
    @SerializedName("3h")
    val threeHour: Double
)

data class Sys(
    @SerializedName("type")
    val type: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("country")
    val country: String,
    @SerializedName("sunrise")
    val sunrise: Int,
    @SerializedName("sunset")
    val sunset: Int
)

data class Clouds(
    @SerializedName("all")
    val all: Int
)