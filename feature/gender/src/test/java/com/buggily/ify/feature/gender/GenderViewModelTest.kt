package com.buggily.ify.feature.gender

import com.buggily.ify.core.model.DataResult
import com.buggily.ify.core.test.CoroutineTestRule
import com.buggily.ify.domain.gender.GenderUi
import com.buggily.ify.domain.gender.GetGenderByName
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GenderViewModelTest {

    private val getGenderByName: GetGenderByName = mockk()
    private lateinit var viewModel: GenderViewModel

    @get:Rule
    val rule = CoroutineTestRule

    @Before
    fun before() {
        clearAllMocks()
        viewModel = GenderViewModel(getGenderByName)
    }

    @Test
    fun `ui state should be default on init`() = runTest {
        Assert.assertEquals(
            GenderUiState.Default,
            viewModel.uiState.value,
        )
    }

    @Test
    fun `ui state should be default on name change with blank name`() = runTest {
        viewModel.onNameChange(String())

        Assert.assertEquals(
            GenderUiState.Default,
            viewModel.uiState.value,
        )
    }

    @Test
    fun `ui state should be response on name change with response`() = runTest {
        val ui = GenderUi(
            gender = null,
            nameText = NAME,
            countText = String(),
            probabilityText = String(),
        )

        coEvery { getGenderByName(NAME) } returns DataResult.Response.Remote(ui)
        viewModel.onNameChange(NAME)

        Assert.assertEquals(
            GenderUiState.Response(ui),
            viewModel.uiState.value,
        )
    }

    @Test
    fun `ui state should be remote api failure on name change with remote api failure`() = runTest {
        coEvery { getGenderByName(NAME) } returns DataResult.Failure.Remote.Api(MESSAGE)
        viewModel.onNameChange(NAME)

        Assert.assertEquals(
            GenderUiState.Failure.Remote.Api(MESSAGE),
            viewModel.uiState.value,
        )
    }

    @Test
    fun `ui state should be remote network failure on name change with remote network failure`() = runTest {
        coEvery { getGenderByName(NAME) } returns DataResult.Failure.Remote.Network(MESSAGE)
        viewModel.onNameChange(NAME)

        Assert.assertEquals(
            GenderUiState.Failure.Remote.Network,
            viewModel.uiState.value,
        )
    }

    @Test
    fun `ui state should be else failure on name change with else failure`() = runTest {
        coEvery { getGenderByName(NAME) } returns DataResult.Failure.Remote.Else(MESSAGE)
        viewModel.onNameChange(NAME)

        Assert.assertEquals(
            GenderUiState.Failure.Else,
            viewModel.uiState.value,
        )
    }

    private companion object {
        private const val NAME = "name"
        private const val MESSAGE = "message"
    }
}
