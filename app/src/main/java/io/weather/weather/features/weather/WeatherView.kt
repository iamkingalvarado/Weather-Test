@file:Suppress("FunctionName")

package io.weather.weather.features.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.weather.weather.R


@Composable
fun WeatherView(viewModel: WeatherViewModel) {
    val state by viewModel.state.collectAsState()

    var text by remember { mutableStateOf(state.query) }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text.orEmpty(),
            onValueChange = { value ->
                text = value
                viewModel.getWeather(value)
            },
            placeholder = {
                Text(text = stringResource(R.string.feat_weather_city_placeholder))
            },
            enabled = true
        )
        state.weather?.let {
            Text(
                text = it.toString()
            )
        }

        if (!state.isLoading) return

        CircularProgressIndicator(
            modifier = Modifier
                .width(64.dp)
                .align(Alignment.CenterHorizontally),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}
