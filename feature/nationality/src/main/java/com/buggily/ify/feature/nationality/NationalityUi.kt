package com.buggily.ify.feature.nationality

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.buggily.ify.core.ui.DefaultText
import com.buggily.ify.core.ui.EndpointBox
import com.buggily.ify.core.ui.ErrorText
import com.buggily.ify.core.ui.LoadingIndicator
import com.buggily.ify.core.ui.SuccessText
import com.buggily.ify.core.ui.R.string as strings

@Composable
fun NationalityScreen(
    uiState: NationalityUiState,
    modifier: Modifier = Modifier,
) {
    val color: Color = when (uiState) {
        is NationalityUiState.Error -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.tertiary
    }

    EndpointBox(
        text = stringResource(R.string.nationalize),
        color = color,
        modifier = modifier,
    ) {
        when (uiState) {
            is NationalityUiState.Success -> NationalitySuccess(
                uiState = uiState,
                modifier = Modifier.fillMaxWidth(),
            )
            is NationalityUiState.Error -> NationalityError(
                uiState = uiState,
                modifier = Modifier.fillMaxWidth(),
            )
            is NationalityUiState.Loading -> NationalityLoading(
                modifier = Modifier,
            )
            is NationalityUiState.Default -> NationalityDefault(
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun NationalitySuccess(
    uiState: NationalityUiState.Success,
    modifier: Modifier = Modifier,
) {
    val text: String = with(uiState) {
        stringResource(
            R.string.nationality_body,
            nameText,
            nationsText ?: stringResource(strings.unknown)
        )
    }

    SuccessText(
        text = text,
        modifier = modifier,
    )
}

@Composable
private fun NationalityError(
    uiState: NationalityUiState.Error,
    modifier: Modifier = Modifier,
) {
    val text: String = when (uiState) {
        is NationalityUiState.Error.Api -> uiState.errorText
        is NationalityUiState.Error.Network -> stringResource(strings.error_network)
        is NationalityUiState.Error.Else -> stringResource(strings.error_else)
    }

    ErrorText(
        text = text,
        modifier = modifier,
    )
}

@Composable
private fun NationalityLoading(
    modifier: Modifier = Modifier,
) {
    LoadingIndicator(
        color = MaterialTheme.colorScheme.tertiary,
        modifier = modifier,
    )
}

@Composable
private fun NationalityDefault(
    modifier: Modifier = Modifier,
) {
    DefaultText(
        text = stringResource(
            strings.enter,
            stringResource(R.string.nationality)
        ),
        modifier = modifier,
    )
}
