package io.weather.weather.features.weather.data.di

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.weather.weather.features.weather.data.DefaultWeatherRepository
import io.weather.weather.features.weather.data.WeatherApi
import io.weather.weather.features.weather.domain.repositories.WeatherRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherModule {

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    private fun provideWeatherApi(retrofit: Retrofit = provideRetrofit()): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    fun provideWeatherRepository(weatherApi: WeatherApi = provideWeatherApi()): WeatherRepository {
        return DefaultWeatherRepository(weatherApi)
    }
}