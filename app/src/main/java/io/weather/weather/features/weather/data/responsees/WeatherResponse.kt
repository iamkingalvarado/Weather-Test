package io.weather.weather.features.weather.data.responsees

import com.google.gson.annotations.SerializedName

/**
 * {"coord":{"lon":-118.2437,"lat":34.0522},"weather":[{"id":802,"main":"Clouds","description":"scattered clouds","icon":"03d"}],"base":"stations","main":{"temp":293.24,"feels_like":293.39,"temp_min":291.48,"temp_max":296.11,"pressure":1015,"humidity":80,"sea_level":1015,"grnd_level":996},"visibility":10000,"wind":{"speed":3.09,"deg":190},"clouds":{"all":40},"dt":1721142128,"sys":{"type":2,"id":38323,"country":"US","sunrise":1721134411,"sunset":1721185456},"timezone":-25200,"id":5368361,"name":"Los Angeles","cod":200}
 */
data class WeatherResponse(
    val coord: Coord,
    val weather: List<WeatherData>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val dt: Int,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)

data class Coord(
    val lon: Double,
    val lat: Double
)

data class Main(
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    val pressure: Int,
    val humidity: Int,
    @SerializedName("sea_level")
    val seaLevel: Int,
    @SerializedName("grnd_level")
    val groundLevel: Int

)

data class WeatherData(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)
