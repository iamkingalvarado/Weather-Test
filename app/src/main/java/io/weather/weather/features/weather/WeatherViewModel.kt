package io.weather.weather.features.weather

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.weather.weather.features.weather.data.di.WeatherModule
import io.weather.weather.features.weather.domain.models.Weather
import io.weather.weather.features.weather.domain.repositories.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.util.concurrent.TimeUnit

data class WeatherState(
    val query: String? = null,
    val isLoading: Boolean = false,
    val weather: Weather? = null,
    val error: Throwable? = null
)

class WeatherViewModel(
    private val weatherRepository: WeatherRepository = WeatherModule.provideWeatherRepository()
) : ViewModel() {

    private val _internalState = MutableStateFlow(WeatherState())
    val state: StateFlow<WeatherState> = _internalState

    private var disposable: Disposable? = null

    fun getWeather(query: String) {
        _internalState.update { it.copy(query = query, weather = null) }
        disposable?.dispose()
        disposable = weatherRepository.getWeather(query)
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(300L, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                _internalState.update { it.copy(isLoading = true) }
            }
            .subscribe(
                { weather ->
                    _internalState.update {
                        it.copy(weather = weather, isLoading = false)
                    }
                },
                { error ->
                    _internalState.update {
                        it.copy(error = error, isLoading = false)
                    }
                }
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}
