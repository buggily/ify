package com.buggily.ify.ui.home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.buggily.ify.R
import com.buggily.ify.core.ui.ext.coerceAtLeast
import com.buggily.ify.feature.age.AgeScreen
import com.buggily.ify.feature.age.AgeViewModel
import com.buggily.ify.feature.gender.GenderScreen
import com.buggily.ify.feature.gender.GenderViewModel
import com.buggily.ify.feature.nationality.NationalityScreen
import com.buggily.ify.feature.nationality.NationalityViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.buggily.ify.core.ui.R.dimen as dimens

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState: HomeUiState by viewModel.uiState.collectAsStateWithLifecycle()

    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val lifecycle: Lifecycle = lifecycleOwner.lifecycle

    val ageViewModel: AgeViewModel = hiltViewModel()
    val genderViewModel: GenderViewModel = hiltViewModel()
    val nationalityViewModel: NationalityViewModel = hiltViewModel()

    LaunchedEffect(viewModel) {
        viewModel.name.flowWithLifecycle(lifecycle).collectLatest {
            launch { ageViewModel.onNameChange(it) }
            launch { genderViewModel.onNameChange(it) }
            launch { nationalityViewModel.onNameChange(it) }
        }
    }

    Box(modifier) {
        HomeScreen(
            uiState = uiState,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Composable
private fun HomeScreen(
    uiState: HomeUiState,
    modifier: Modifier,
) {
    HomeScreen(
        nameState = uiState.nameState,
        modifier = modifier,
    )
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun HomeScreen(
    nameState: HomeUiState.NameState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = WindowInsets.safeContent.asPaddingValues().coerceAtLeast(
            padding = dimensionResource(dimens.padding),
        ),
        verticalArrangement = Arrangement.spacedBy(
            space = dimensionResource(dimens.padding),
            alignment = Alignment.Top,
        ),
        horizontalAlignment = Alignment.Start,
        modifier = modifier.consumeWindowInsets(WindowInsets.safeContent),
    ) {
        val contentModifier: Modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()

        stickyHeader {
            HomeBar(
                nameState = nameState,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        item {
            AgeScreen(
                viewModel = hiltViewModel(),
                modifier = contentModifier,
            )
        }

        item {
            GenderScreen(
                viewModel = hiltViewModel(),
                modifier = contentModifier,
            )
        }

        item {
            NationalityScreen(
                viewModel = hiltViewModel(),
                modifier = contentModifier,
            )
        }
    }
}

@Composable
private fun HomeBar(
    nameState: HomeUiState.NameState,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier,
    ) {
        HomeTextField(
            nameState = nameState,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun HomeTextField(
    nameState: HomeUiState.NameState,
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
    nameState: HomeUiState.NameState,
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
