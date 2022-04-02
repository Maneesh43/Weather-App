package com.maneesh.weather.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maneesh.weather.network.NetworkService
import com.maneesh.weather.network.networkModels.CurrentWeatherModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class WeatherViewModel: ViewModel() {


    fun getCurrentData(): CurrentWeatherModel? {
        var currentWeatherData:CurrentWeatherModel?=null
        viewModelScope.launch {
            val weather=service.getCurrentWeather()
           weather.enqueue(object:Callback<CurrentWeatherModel>{
                override fun onResponse(
                    call: Call<CurrentWeatherModel>,
                    response: Response<CurrentWeatherModel>
                ) {
                    currentWeatherData=response.body()
                }

                override fun onFailure(call: Call<CurrentWeatherModel>, t: Throwable) {

                }

            })
        }
        return currentWeatherData
    }

   companion object{
       private const val url:String=""
       private val retrofit: Retrofit by lazy {
           Retrofit.Builder().baseUrl(url).addConverterFactory(MoshiConverterFactory.create()).build()
       }
       val service: NetworkService by lazy{
           retrofit.create(NetworkService::class.java)
       }
   }

}