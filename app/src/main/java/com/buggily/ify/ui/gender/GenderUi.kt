package com.buggily.ify.ui.gender

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.buggily.ify.R
import com.buggily.ify.ui.ext.EndpointBox
import com.buggily.ify.ui.home.HomeState

@Composable
fun GenderScreen(
    genderState: HomeState.GenderState,
    modifier: Modifier = Modifier,
) {
    val color: Color = when (genderState) {
        is HomeState.GenderState.Error -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.secondary
    }

    EndpointBox(
        text = stringResource(R.string.genderize),
        color = color,
        modifier = modifier,
    ) {
        when (genderState) {
            is HomeState.GenderState.Success -> GenderSuccess(
                genderState = genderState,
                modifier = Modifier.fillMaxWidth(),
            )
            is HomeState.GenderState.Error -> GenderError(
                genderState = genderState,
                modifier = Modifier.fillMaxWidth(),
            )
            is HomeState.GenderState.Loading -> GenderLoading(
                modifier = Modifier,
            )
            is HomeState.GenderState.Default -> GenderDefault(
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun GenderSuccess(
    genderState: HomeState.GenderState.Success,
    modifier: Modifier = Modifier,
) {
    val text: String = genderState.run {
        stringResource(
            R.string.gender_body,
            nameDisplay,
            genderDisplay,
            percentageDisplay,
            countDisplay,
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
    genderState: HomeState.GenderState.Error,
    modifier: Modifier = Modifier,
) {
    Text(
        text = genderState.errorDisplay,
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
        text = stringResource(R.string.enter, stringResource(R.string.gender)),
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier,
    )
}
