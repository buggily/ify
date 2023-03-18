package com.buggily.ify.feature.gender

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
fun GenderScreen(
    uiState: GenderUiState,
    modifier: Modifier = Modifier,
) {
    val color: Color = when (uiState) {
        is GenderUiState.Error -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.secondary
    }

    EndpointBox(
        text = stringResource(R.string.genderize),
        color = color,
        modifier = modifier,
    ) {
        when (uiState) {
            is GenderUiState.Success -> GenderSuccess(
                uiState = uiState,
                modifier = Modifier.fillMaxWidth(),
            )
            is GenderUiState.Error -> GenderError(
                uiState = uiState,
                modifier = Modifier.fillMaxWidth(),
            )
            is GenderUiState.Loading -> GenderLoading(
                modifier = Modifier,
            )
            is GenderUiState.Default -> GenderDefault(
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun GenderSuccess(
    uiState: GenderUiState.Success,
    modifier: Modifier = Modifier,
) {
    val text: String = with(uiState) {
        stringResource(
            R.string.gender_body,
            nameText,
            genderText ?: stringResource(strings.unknown),
            percentageText,
            countText
        )
    }

    SuccessText(
        text = text,
        modifier = modifier,
    )
}

@Composable
private fun GenderError(
    uiState: GenderUiState.Error,
    modifier: Modifier = Modifier,
) {
    val text: String = when (uiState) {
        is GenderUiState.Error.Api -> uiState.errorText
        is GenderUiState.Error.Network -> stringResource(strings.error_network)
        is GenderUiState.Error.Else -> stringResource(strings.error_else)
    }

    ErrorText(
        text = text,
        modifier = modifier,
    )
}

@Composable
private fun GenderLoading(
    modifier: Modifier = Modifier,
) {
    LoadingIndicator(
        color = MaterialTheme.colorScheme.secondary,
        modifier = modifier,
    )
}

@Composable
private fun GenderDefault(
    modifier: Modifier = Modifier,
) {
    DefaultText(
        text = stringResource(
            strings.enter,
            stringResource(R.string.gender)
        ),
        modifier = modifier,
    )
}
