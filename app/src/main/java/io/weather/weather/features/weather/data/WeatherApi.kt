package io.weather.weather.features.weather.data

import io.reactivex.rxjava3.core.Observable
import io.weather.weather.features.weather.data.responsees.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    fun getWeather(
        @Query("q") query: String,
        @Query("appId") appId: String = "5deca2f99f97d972562a33188d696b4a"
    ): Observable<WeatherResponse>
}
