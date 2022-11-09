package com.buggily.ify.feature.age

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
    val text: String = ageState.run {
        stringResource(
            R.string.age_body,
            nameText,
            ageText,
            countText
        )
    }

    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier,
    )
}

@Composable
private fun AgeError(
    ageState: AgeState.Error,
    modifier: Modifier = Modifier,
) {
    Text(
        text = ageState.errorText,
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier,
    )
}

@Composable
private fun AgeLoading(
    modifier: Modifier = Modifier,
) {
    CircularProgressIndicator(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier,
    )
}

@Composable
private fun AgeDefault(
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(
            RUi.string.enter,
            stringResource(R.string.age)
        ),
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier,
    )
}
