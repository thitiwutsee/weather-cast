# WeatherCast App

## Overview
WeatherCast is a mobile application built with Android Jetpack Compose that provides weather updates with city names.
The app displays current weather conditions such as temperature, humidity, wind speed, sunrise and sunset times, and more for a selected location. It uses data from OpenWeatherMap API for fetching weather details.


## Features
- Weather Updates By city name: Get current weather data, including temperature, humidity, and wind speed.
- Sunrise and Sunset: Displays sunrise and sunset times
- Weather Icon: Shows icons representing different weather conditions (rain, clouds, sun, etc.).
- Weather Event: Display volume for the last 1 hour on Snow or Rain
- Local Data Storage (Room Database): Stores history location search text

## Screenshots
![Screenshot 2567-09-16 at 14 31 32](https://github.com/user-attachments/assets/64634575-fd1c-471b-8826-a7669e7a5cbf)

## Technologies Used
-  Kotlin: The main programming language used in Android development.
-  Jetpack Compose: Modern Android UI toolkit used to build the app’s interface.
-  Hilt: Dependency injection framework used to manage app components.
-  OpenWeatherMap API: Weather data provider used to fetch weather information.
-  Coroutines & Flow: For managing asynchronous tasks and network calls.
-  Retrofit: HTTP client used for networking and consuming the OpenWeatherMap API.
-  Room Database: Local database

## Prerequisites
- Android Studio 4.2 or above
- Android SDK 30 or above

## Setup Instructions
1. Clond the repository
   ```bash
    git clone https://github.com/thitiwutsee/weather-cast.git
   ```
2. Open the project in Android Studio
3. Build and run the app on your Android device or emulator

## Usage
- Launch the app on your Android device.
- Enter city name
- Data displayed includes: Temperature, Humidity, Wind speed, Sunrise and sunset times, etc.



