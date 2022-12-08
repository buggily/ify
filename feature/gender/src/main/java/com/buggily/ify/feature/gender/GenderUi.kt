package com.buggily.ify.feature.gender

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.buggily.ify.core.ui.EndpointBox
import com.buggily.ify.core.ui.R.string as strings

@Composable
fun GenderScreen(
    genderState: GenderState,
    modifier: Modifier = Modifier,
) {
    val color: Color = when (genderState) {
        is GenderState.Error -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.secondary
    }

    EndpointBox(
        text = stringResource(R.string.genderize),
        color = color,
        modifier = modifier,
    ) {
        when (genderState) {
            is GenderState.Success -> GenderSuccess(
                genderState = genderState,
                modifier = Modifier.fillMaxWidth(),
            )
            is GenderState.Error -> GenderError(
                genderState = genderState,
                modifier = Modifier.fillMaxWidth(),
            )
            is GenderState.Loading -> GenderLoading(
                modifier = Modifier,
            )
            is GenderState.Default -> GenderDefault(
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun GenderSuccess(
    genderState: GenderState.Success,
    modifier: Modifier = Modifier,
) {
    val text: String = genderState.run {
        stringResource(
            R.string.gender_body,
            nameText,
            genderText,
            percentageText,
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
private fun GenderError(
    genderState: GenderState.Error,
    modifier: Modifier = Modifier,
) {
    Text(
        text = genderState.errorText,
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier,
    )
}

@Composable
private fun GenderLoading(
    modifier: Modifier = Modifier,
) {
    CircularProgressIndicator(
        color = MaterialTheme.colorScheme.secondary,
        modifier = modifier,
    )
}

@Composable
private fun GenderDefault(
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(
            strings.enter,
            stringResource(R.string.gender)
        ),
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier,
    )
}
