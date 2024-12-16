package com.buggily.ify.feature.age

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
fun AgeScreen(
    viewModel: AgeViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState: AgeUiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier) {
        AgeScreen(
            uiState = uiState,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun AgeScreen(
    uiState: AgeUiState,
    modifier: Modifier = Modifier,
) {
    val color: Color = when (uiState) {
        is AgeUiState.Failure -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.primary
    }

    EndpointBox(
        endpointText = stringResource(R.string.agify),
        color = color,
        modifier = modifier,
    ) {
        when (uiState) {
            is AgeUiState.Response -> AgeResponse(
                uiState = uiState,
                modifier = Modifier.fillMaxWidth(),
            )
            is AgeUiState.Failure -> AgeFailure(
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
private fun AgeResponse(
    uiState: AgeUiState.Response,
    modifier: Modifier = Modifier,
) {
    val text: String = with(uiState.age) {
        stringResource(
            R.string.age_body,
            nameText,
            ageText ?: stringResource(CR.string.unknown),
            countText
        )
    }

    ResponseText(
        text = text,
        modifier = modifier,
    )
}

@Composable
private fun AgeFailure(
    uiState: AgeUiState.Failure,
    modifier: Modifier = Modifier,
) {
    val text: String = when (uiState) {
        is AgeUiState.Failure.Remote.Api -> uiState.message
        is AgeUiState.Failure.Remote.Network -> stringResource(CR.string.error_network)
        is AgeUiState.Failure.Else -> stringResource(CR.string.error_unknown)
    }

    FailureText(
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
            CR.string.enter,
            stringResource(R.string.age)
        ),
        modifier = modifier,
    )
}
