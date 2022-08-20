package com.buggily.ify.ui.nationality

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.buggily.ify.R
import com.buggily.ify.ui.ext.EndpointBox
import com.buggily.ify.ui.home.HomeState

@Composable
fun NationalityScreen(
    nationalityState: HomeState.NationalityState,
    modifier: Modifier = Modifier,
) {
    val color: Color = when (nationalityState) {
        is HomeState.NationalityState.Error -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.tertiary
    }

    EndpointBox(
        text = stringResource(R.string.nationalize),
        color = color,
        modifier = modifier,
    ) {
        when (nationalityState) {
            is HomeState.NationalityState.Success -> NationalityState(
                nationalityState = nationalityState,
                modifier = Modifier.fillMaxWidth(),
            )
            is HomeState.NationalityState.Error -> NationalityError(
                nationalityState = nationalityState,
                modifier = Modifier.fillMaxWidth(),
            )
            is HomeState.NationalityState.Loading -> NationalityLoading(
                modifier = Modifier,
            )
            is HomeState.NationalityState.Default -> NationalityDefault(
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun NationalityState(
    nationalityState: HomeState.NationalityState.Success,
    modifier: Modifier = Modifier,
) {
    val text: String = nationalityState.run {
        stringResource(
            R.string.nationality_body,
            nameDisplay,
            countriesDisplay ?: stringResource(R.string.nonexistant),
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
    nationalityState: HomeState.NationalityState.Error,
    modifier: Modifier = Modifier,
) {
    Text(
        text = nationalityState.errorDisplay,
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
        text = stringResource(R.string.enter, stringResource(R.string.nationality)),
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier,
    )
}
