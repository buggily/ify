package com.buggily.ify.ui

import com.buggily.ify.core.domain.FormatCapitalize
import com.buggily.ify.core.test.CoroutineTestRule
import io.mockk.clearAllMocks
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Locale

class IfyViewModelTest {

    private val formatCapitalize = FormatCapitalize(Locale.getDefault())
    private lateinit var viewModel: IfyViewModel

    @get:Rule
    val rule = CoroutineTestRule(StandardTestDispatcher())

    @Before
    fun before() {
        clearAllMocks()
        viewModel = IfyViewModel(formatCapitalize)
    }

    @Test
    fun nameIsDefaultNameOnInit() = runTest {
        Assert.assertEquals(
            IfyViewModel.DEFAULT_NAME,
            viewModel.uiState.value.nameState.value,
        )
    }

    @Test
    fun onNameChangeUpdatesName() = runTest {
        Assert.assertNotEquals(
            NAME,
            viewModel.uiState.value.nameState.value,
        )

        viewModel.uiState.value.nameState.onValueChange(NAME)

        Assert.assertEquals(
            NAME,
            viewModel.uiState.value.nameState.value,
        )
    }

    @Test
    fun onClearNameResetsName() = runTest {
        Assert.assertNotEquals(
            String(),
            viewModel.uiState.value.nameState.value,
        )

        viewModel.uiState.value.nameState.onValueClear?.invoke()

        Assert.assertEquals(
            String(),
            viewModel.uiState.value.nameState.value,
        )
    }

    @Test
    fun onValueClearIsNotNullWhenNameIsNotEmpty() {
        viewModel.uiState.value.nameState.onValueChange(NAME)
        Assert.assertNotNull(viewModel.uiState.value.nameState.onValueClear)
    }

    @Test
    fun onValueClearIsNullWhenNameIsEmpty() {
        viewModel.uiState.value.nameState.onValueChange(String())
        Assert.assertNull(viewModel.uiState.value.nameState.onValueClear)
    }

    private companion object {
        private const val NAME = "name"
    }
}
