package com.maneesh.weather.network
import com.maneesh.weather.network.networkModels.CurrentWeatherModel
import retrofit2.Call
import retrofit2.http.GET


interface NetworkService {

@GET("/")
suspend fun getCurrentWeather():Call<CurrentWeatherModel>

}