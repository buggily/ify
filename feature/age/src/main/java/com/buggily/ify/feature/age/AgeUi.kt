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
    ageState: AgeState,
    modifier: Modifier = Modifier,
) {
    val color: Color = when (ageState) {
        is AgeState.Error -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.primary
    }

    EndpointBox(
        text = stringResource(R.string.agify),
        color = color,
        modifier = modifier,
    ) {
        when (ageState) {
            is AgeState.Success -> AgeSuccess(
                ageState = ageState,
                modifier = Modifier.fillMaxWidth(),
            )
            is AgeState.Error -> AgeError(
                ageState = ageState,
                modifier = Modifier.fillMaxWidth(),
            )
            is AgeState.Loading -> AgeLoading(
                modifier = Modifier,
            )
            is AgeState.Default -> AgeDefault(
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun AgeSuccess(
    ageState: AgeState.Success,
    modifier: Modifier = Modifier,
) {
    val text: String = with(ageState) {
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
    ageState: AgeState.Error,
    modifier: Modifier = Modifier,
) {
    ErrorText(
        text = ageState.errorText,
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
