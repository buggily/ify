package com.buggily.ify.feature.age

import ageText
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
import countText
import errorText
import nameText
import com.buggily.ify.core.ui.R.string as strings

@Composable
fun AgeScreen(
    uiState: AgeUiState,
    modifier: Modifier = Modifier,
) {
    val color: Color = when (uiState) {
        is AgeUiState.Error -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.primary
    }

    EndpointBox(
        text = stringResource(R.string.agify),
        color = color,
        modifier = modifier,
    ) {
        when (uiState) {
            is AgeUiState.Success -> AgeSuccess(
                uiState = uiState,
                modifier = Modifier.fillMaxWidth(),
            )
            is AgeUiState.Error -> AgeError(
                uiState = uiState,
                modifier = Modifier.fillMaxWidth(),
            )
            is AgeUiState.Loading -> AgeLoading(
                modifier = Modifier,
            )
            is AgeUiState.Default -> AgeDefault(
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun AgeSuccess(
    uiState: AgeUiState.Success,
    modifier: Modifier = Modifier,
) {
    val text: String = with(uiState) {
        stringResource(
            R.string.age_body,
            nameText,
            ageText ?: stringResource(strings.unknown),
            countText
        )
    }

    SuccessText(
        text = text,
        modifier = modifier,
    )
}

@Composable
private fun AgeError(
    uiState: AgeUiState.Error,
    modifier: Modifier = Modifier,
) {
    val text: String = when (uiState) {
        is AgeUiState.Error.Api -> uiState.errorText
        is AgeUiState.Error.Network -> stringResource(strings.error_network)
        is AgeUiState.Error.Else -> stringResource(strings.error_else)
    }

    ErrorText(
        text = text,
        modifier = modifier,
    )
}

@Composable
private fun AgeLoading(
    modifier: Modifier = Modifier,
) {
    LoadingIndicator(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier,
    )
}

@Composable
private fun AgeDefault(
    modifier: Modifier = Modifier,
) {
    DefaultText(
        text = stringResource(
            strings.enter,
            stringResource(R.string.age)
        ),
        modifier = modifier,
    )
}
