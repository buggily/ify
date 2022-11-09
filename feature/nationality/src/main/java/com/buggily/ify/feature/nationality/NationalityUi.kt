package com.buggily.ify.feature.nationality

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.buggily.ify.core.ui.EndpointBox
import com.buggily.ify.core.ui.R as RUi

@Composable
fun NationalityScreen(
    nationalityState: NationalityState,
    modifier: Modifier = Modifier,
) {
    val color: Color = when (nationalityState) {
        is NationalityState.Error -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.tertiary
    }

    EndpointBox(
        text = stringResource(R.string.nationalize),
        color = color,
        modifier = modifier,
    ) {
        when (nationalityState) {
            is NationalityState.Success -> NationalityState(
                nationalityState = nationalityState,
                modifier = Modifier.fillMaxWidth(),
            )
            is NationalityState.Error -> NationalityError(
                nationalityState = nationalityState,
                modifier = Modifier.fillMaxWidth(),
            )
            is NationalityState.Loading -> NationalityLoading(
                modifier = Modifier,
            )
            is NationalityState.Default -> NationalityDefault(
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun NationalityState(
    nationalityState: NationalityState.Success,
    modifier: Modifier = Modifier,
) {
    val text: String = nationalityState.run {
        stringResource(
            R.string.nationality_body,
            nameText,
            countriesText ?: stringResource(RUi.string.nonexistant)
        )
    }

    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier,
    )
}

@Composable
private fun NationalityError(
    nationalityState: NationalityState.Error,
    modifier: Modifier = Modifier,
) {
    Text(
        text = nationalityState.errorText,
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier,
    )
}

@Composable
private fun NationalityLoading(
    modifier: Modifier = Modifier,
) {
    CircularProgressIndicator(
        color = MaterialTheme.colorScheme.tertiary,
        modifier = modifier,
    )
}

@Composable
private fun NationalityDefault(
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(
            RUi.string.enter,
            stringResource(R.string.nationality)
        ),
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier,
    )
}
