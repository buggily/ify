package com.buggily.ify.feature.nationality

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.buggily.ify.core.ui.ui.DefaultText
import com.buggily.ify.core.ui.ui.EndpointBox
import com.buggily.ify.core.ui.ui.FailureText
import com.buggily.ify.core.ui.ui.LoadingIndicator
import com.buggily.ify.core.ui.ui.ResponseText
import com.buggily.ify.core.ui.R as CR

@Composable
fun NationalityScreen(
    viewModel: NationalityViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState: NationalityUiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier) {
        NationalityScreen(
            uiState = uiState,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun NationalityScreen(
    uiState: NationalityUiState,
    modifier: Modifier = Modifier,
) {
    val color: Color = when (uiState) {
        is NationalityUiState.Failure -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.tertiary
    }

    EndpointBox(
        text = stringResource(R.string.nationalize),
        color = color,
        modifier = modifier,
    ) {
        when (uiState) {
            is NationalityUiState.Response -> NationalityResponse(
                uiState = uiState,
                modifier = Modifier.fillMaxWidth(),
            )
            is NationalityUiState.Failure -> NationalityFailure(
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
private fun NationalityResponse(
    uiState: NationalityUiState.Response,
    modifier: Modifier = Modifier,
) {
    val text: String = with(uiState.nationality) {
        stringResource(
            R.string.nationality_body,
            nameText,
            nationsText ?: stringResource(CR.string.unknown),
            countText,
        )
    }

    ResponseText(
        text = text,
        modifier = modifier,
    )
}

@Composable
private fun NationalityFailure(
    uiState: NationalityUiState.Failure,
    modifier: Modifier = Modifier,
) {
    val text: String = when (uiState) {
        is NationalityUiState.Failure.Remote.Api -> uiState.message
        is NationalityUiState.Failure.Remote.Network -> stringResource(CR.string.error_network)
        is NationalityUiState.Failure.Else -> stringResource(CR.string.error_unknown)
    }

    FailureText(
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
            CR.string.enter,
            stringResource(R.string.nationality)
        ),
        modifier = modifier,
    )
}
