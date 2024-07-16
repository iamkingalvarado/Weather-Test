package io.weather.weather.features.weather.domain.repositories

import io.reactivex.rxjava3.core.Observable
import io.weather.weather.features.weather.domain.models.Weather

interface WeatherRepository {
    fun getWeather(query: String): Observable<Weather>
}
