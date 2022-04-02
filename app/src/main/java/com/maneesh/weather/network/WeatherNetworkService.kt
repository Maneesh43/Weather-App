package com.maneesh.weather.network
import com.maneesh.weather.network.networkModels.CurrentWeatherModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET

const val BASE_URL="https://"
interface WeatherNetworkService {

@GET("/")
suspend fun getCurrentWeather():Call<CurrentWeatherModel>
}

val retrofit=Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create()).build()

object WeatherNetwork{
    val retrofitService:WeatherNetworkService by lazy { retrofit.create(WeatherNetworkService::class.java) }
}