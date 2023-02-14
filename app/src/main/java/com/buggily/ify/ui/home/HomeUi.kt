package com.buggily.ify.ui.home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.buggily.ify.R
import com.buggily.ify.feature.age.AgeScreen
import com.buggily.ify.feature.gender.GenderScreen
import com.buggily.ify.feature.nationality.NationalityScreen
import com.buggily.ify.core.ui.R.dimen as dimens

@Composable
@OptIn(ExperimentalLifecycleComposeApi::class)
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
) {
    val state: HomeState by viewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        state = state,
        modifier = modifier,
    )
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun HomeScreen(
    state: HomeState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(dimens.padding)),
        verticalArrangement = Arrangement.spacedBy(
            space = dimensionResource(dimens.padding),
            alignment = Alignment.Top,
        ),
        horizontalAlignment = Alignment.Start,
        modifier = modifier,
    ) {
        val contentModifier: Modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()

        stickyHeader {
            HomeBar(
                state = state,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        item {
            AgeScreen(
                ageState = state.ageState,
                modifier = contentModifier,
            )
        }

        item {
            GenderScreen(
                genderState = state.genderState,
                modifier = contentModifier,
            )
        }

        item {
            NationalityScreen(
                nationalityState = state.nationalityState,
                modifier = contentModifier,
            )
        }
    }
}

@Composable
private fun HomeBar(
    state: HomeState,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier,
    ) {
        HomeTextField(
            nameState = state.nameState,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun HomeTextField(
    nameState: HomeState.NameState,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = nameState.name,
        onValueChange = nameState.onChange,
        label = { HomeTextFieldLabel() },
        trailingIcon = { HomeTextFieldTrailingIcon(nameState) },
        modifier = modifier,
    )
}

@Composable
private fun HomeTextFieldLabel(
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(R.string.name),
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier,
    )
}

@Composable
private fun HomeTextFieldTrailingIcon(
    nameState: HomeState.NameState,
    modifier: Modifier = Modifier,
) {
    val onClick: () -> Unit = nameState.onClear ?: return

    IconButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(
            painter = painterResource(R.drawable.clear),
            contentDescription = stringResource(R.string.clear),
        )
    }
}
