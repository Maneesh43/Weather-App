package com.maneesh.weather.network
import com.maneesh.weather.network.networkModels.CurrentWeatherModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL="https://api.openweathermap.org/data/2.5"
interface WeatherNetworkService {

@GET("/weather")
suspend fun getCurrentWeather(@Query("lat") lat:Int,@Query("lon") lon:Int,@Query("appid") apiKey:String="5a5442134945fbd734e9a2fda575ef30"):Call<CurrentWeatherModel>
}

val retrofit=Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create()).build()

object WeatherNetwork{
    val retrofitService:WeatherNetworkService by lazy { retrofit.create(WeatherNetworkService::class.java) }
}