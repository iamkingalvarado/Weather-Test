package io.weather.weather.features.weather.domain.models

data class Weather(
    val temperature: Double,
    val feelsLike: Double,
    val maxTemperature: Double,
    val minTemperature: Double,
    val pressure: Int,
    val humidity: Int,
    val seaLevel: Int,
    val groundLevel: Int
) {
    override fun toString() = """
        Temperature: $temperature
        Feels like: $feelsLike
        Max temperature: $maxTemperature
        Min temperature: $minTemperature
        Pressure: $pressure
        Humidity: $humidity
        Sea level: $seaLevel
        Ground level: $groundLevel
    """
}