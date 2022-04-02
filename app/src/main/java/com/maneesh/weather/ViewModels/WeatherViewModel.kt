package com.maneesh.weather.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maneesh.weather.network.WeatherNetwork
import com.maneesh.weather.network.networkModels.CurrentWeatherModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class WeatherViewModel : ViewModel() {
    private val _weatherData = MutableLiveData<CurrentWeatherModel>()
    val weatherData: LiveData<CurrentWeatherModel> = _weatherData


    init{
        getCurrentWeather()
    }

    private fun getCurrentWeather(){
        viewModelScope.launch {
           try{
               WeatherNetwork.retrofitService.getCurrentWeather()
                   .enqueue(object : Callback<CurrentWeatherModel> {
                       override fun onResponse(
                           call: Call<CurrentWeatherModel>,
                           response: Response<CurrentWeatherModel>
                       ) {
                           _weatherData.postValue(response.body())
                       }

                       override fun onFailure(call: Call<CurrentWeatherModel>, t: Throwable) {
                           Log.d("Network error", "onFailure: ${t.localizedMessage}")
                       }

                   })
           }catch (e:Exception){
               
           }
        }
    }


}