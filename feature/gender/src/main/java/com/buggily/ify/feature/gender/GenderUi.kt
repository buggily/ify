package com.buggily.ify.feature.gender

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.buggily.ify.core.ui.DefaultText
import com.buggily.ify.core.ui.EndpointBox
import com.buggily.ify.core.ui.FailureText
import com.buggily.ify.core.ui.LoadingIndicator
import com.buggily.ify.core.ui.ResponseText
import com.buggily.ify.data.gender.Gender
import com.buggily.ify.core.ui.R.string as strings

@Composable
fun GenderScreen(
    uiState: GenderUiState,
    modifier: Modifier = Modifier,
) {
    val color: Color = when (uiState) {
        is GenderUiState.Failure -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.secondary
    }

    EndpointBox(
        text = stringResource(R.string.genderize),
        color = color,
        modifier = modifier,
    ) {
        when (uiState) {
            is GenderUiState.Response -> GenderResponse(
                uiState = uiState,
                modifier = Modifier.fillMaxWidth(),
            )
            is GenderUiState.Failure -> GenderFailure(
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
private fun GenderResponse(
    uiState: GenderUiState.Response,
    modifier: Modifier = Modifier,
) {
    val genderStringResId: Int? = when (uiState.gender.gender) {
        is Gender.Gender.Male -> R.string.gender_male
        is Gender.Gender.Female -> R.string.gender_female
        else -> null
    }

    val text: String = with(uiState) {
        val genderText: String = genderStringResId?.let {
            getGenderText(stringResource(it))
        } ?: stringResource(strings.unknown)

        stringResource(
            R.string.gender_body,
            nameText,
            genderText,
            percentageText,
            countText
        )
    }

    ResponseText(
        text = text,
        modifier = modifier,
    )
}

@Composable
private fun GenderFailure(
    uiState: GenderUiState.Failure,
    modifier: Modifier = Modifier,
) {
    val text: String = when (uiState) {
        is GenderUiState.Failure.Remote.Api -> uiState.failureText
        is GenderUiState.Failure.Remote.Network -> stringResource(strings.error_network)
        is GenderUiState.Failure.Else -> stringResource(strings.error_else)
    }

    FailureText(
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
