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
            is NationalityState.Success -> NationalitySuccess(
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
private fun NationalitySuccess(
    nationalityState: NationalityState.Success,
    modifier: Modifier = Modifier,
) {
    val text: String = with(nationalityState) {
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
    nationalityState: NationalityState.Error,
    modifier: Modifier = Modifier,
) {
    ErrorText(
        text = nationalityState.errorText,
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
