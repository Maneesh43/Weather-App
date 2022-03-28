package com.maneesh.weather.network
import com.maneesh.weather.network.networkModels.currentWeather
import retrofit2.http.GET


interface NetworkService {

@GET("/")
suspend fun getCurrentWeather():currentWeather

}