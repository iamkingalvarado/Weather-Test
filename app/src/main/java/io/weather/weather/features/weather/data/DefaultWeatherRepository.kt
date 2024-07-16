package io.weather.weather.features.weather.data

import io.reactivex.rxjava3.core.Observable
import io.weather.weather.features.weather.domain.models.Weather
import io.weather.weather.features.weather.domain.repositories.WeatherRepository

class DefaultWeatherRepository(
    private val weatherApi: WeatherApi
) : WeatherRepository {
    override fun getWeather(query: String): Observable<Weather> {
        return weatherApi.getWeather(query).map { response ->
            Weather(
                temperature = response.main.temp,
                feelsLike = response.main.feelsLike,
                maxTemperature = response.main.tempMax,
                minTemperature = response.main.tempMin,
                pressure = response.main.pressure,
                humidity = response.main.humidity,
                seaLevel = response.main.seaLevel,
                groundLevel = response.main.groundLevel,
            )
        }
    }
}