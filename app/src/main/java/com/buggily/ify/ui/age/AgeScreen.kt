package com.buggily.ify.ui.age

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.buggily.ify.R
import com.buggily.ify.data.age.Age
import com.buggily.ify.ui.ext.EndpointBox
import com.buggily.ify.ui.home.HomeState

@Composable
fun AgeScreen(
    ageState: HomeState.AgeState,
    modifier: Modifier = Modifier,
) {
    val color: Color = when (ageState) {
        is HomeState.AgeState.Error -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.primary
    }

    EndpointBox(
        text = stringResource(R.string.agify),
        color = color,
        modifier = modifier,
    ) {
        when (ageState) {
            is HomeState.AgeState.Success -> AgeSuccess(
                ageState = ageState,
                modifier = Modifier.fillMaxWidth(),
            )
            is HomeState.AgeState.Error -> AgeError(
                ageState = ageState,
                modifier = Modifier.fillMaxWidth(),
            )
            is HomeState.AgeState.Loading -> AgeLoading(
                modifier = Modifier,
            )
            is HomeState.AgeState.Default -> AgeDefault(
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun AgeSuccess(
    ageState: HomeState.AgeState.Success,
    modifier: Modifier = Modifier,
) {
    val text: String = ageState.age.run {
        stringResource(
            R.string.age_body,
            name,
            age.toString(),
            count.toString(),
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
    ageState: HomeState.AgeState.Error,
    modifier: Modifier = Modifier,
) {
    Text(
        text = ageState.error,
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
        text = stringResource(R.string.enter, stringResource(R.string.age)),
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier,
    )
}
