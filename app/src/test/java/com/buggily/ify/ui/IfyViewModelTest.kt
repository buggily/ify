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
    fun `name is default name on init`() = runTest {
        Assert.assertEquals(
            IfyViewModel.DEFAULT_NAME,
            viewModel.uiState.value.nameState.name,
        )
    }

    @Test
    fun `on name change updates name`() = runTest {
        Assert.assertNotEquals(
            NAME,
            viewModel.uiState.value.nameState.name,
        )

        viewModel.uiState.value.nameState.onChange(NAME)

        Assert.assertEquals(
            NAME,
            viewModel.uiState.value.nameState.name,
        )
    }

    @Test
    fun `on clear name resets name`() = runTest {
        Assert.assertNotEquals(
            String(),
            viewModel.uiState.value.nameState.name,
        )

        viewModel.uiState.value.nameState.onClear?.invoke()

        Assert.assertEquals(
            String(),
            viewModel.uiState.value.nameState.name,
        )
    }

    @Test
    fun `on clear is not null when name is not empty`() {
        viewModel.uiState.value.nameState.onChange(NAME)
        Assert.assertNotNull(viewModel.uiState.value.nameState.onClear)
    }

    @Test
    fun `on clear is null when name is empty`() {
        viewModel.uiState.value.nameState.onChange(String())
        Assert.assertNull(viewModel.uiState.value.nameState.onClear)
    }

    private companion object {
        private const val NAME = "name"
    }
}
