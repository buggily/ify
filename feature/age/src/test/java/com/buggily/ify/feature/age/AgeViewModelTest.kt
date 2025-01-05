package com.buggily.ify.feature.age

import com.buggily.ify.core.model.DataResult
import com.buggily.ify.core.test.CoroutineTestRule
import com.buggily.ify.domain.age.AgeUi
import com.buggily.ify.domain.age.GetAgeByName
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AgeViewModelTest {

    private val getAgeByName: GetAgeByName = mockk()
    private lateinit var viewModel: AgeViewModel

    @get:Rule
    val rule = CoroutineTestRule(StandardTestDispatcher())

    @Before
    fun before() {
        clearAllMocks()
        viewModel = AgeViewModel(getAgeByName)
    }

    @Test
    fun `ui state is default on init`() = runTest {
        Assert.assertEquals(
            AgeUiState.Default,
            viewModel.uiState.value,
        )
    }

    @Test
    fun `ui state is default on name change with blank name`() = runTest {
        viewModel.onNameChange(String())

        Assert.assertEquals(
            AgeUiState.Default,
            viewModel.uiState.value,
        )
    }

    @Test
    fun `ui state is loading on name change with name`() = runTest {
        coEvery { getAgeByName(NAME) } returns DataResult.Failure.Local
        viewModel.onNameChange(NAME)

        Assert.assertEquals(
            AgeUiState.Loading,
            viewModel.uiState.value,
        )
    }

    @Test
    fun `ui state is response on name change with response`() = runTest {
        val ui = AgeUi(
            nameText = NAME,
            ageText = String(),
            countText = String(),
        )

        coEvery { getAgeByName(NAME) } returns DataResult.Response.Remote(ui)
        viewModel.onNameChange(NAME)
        runCurrent()

        Assert.assertEquals(
            AgeUiState.Response(ui),
            viewModel.uiState.value,
        )
    }

    @Test
    fun `ui state is remote api failure on name change with remote api failure`() = runTest {
        coEvery { getAgeByName(NAME) } returns DataResult.Failure.Remote.Api(MESSAGE)
        viewModel.onNameChange(NAME)
        runCurrent()

        Assert.assertEquals(
            AgeUiState.Failure.Remote.Api(MESSAGE),
            viewModel.uiState.value,
        )
    }

    @Test
    fun `ui state is remote network failure on name change with remote network failure`() = runTest {
        coEvery { getAgeByName(NAME) } returns DataResult.Failure.Remote.Network(MESSAGE)
        viewModel.onNameChange(NAME)
        runCurrent()

        Assert.assertEquals(
            AgeUiState.Failure.Remote.Network,
            viewModel.uiState.value,
        )
    }

    @Test
    fun `ui state is else failure on name change with else failure`() = runTest {
        coEvery { getAgeByName(NAME) } returns DataResult.Failure.Remote.Else(MESSAGE)
        viewModel.onNameChange(NAME)
        runCurrent()

        Assert.assertEquals(
            AgeUiState.Failure.Else,
            viewModel.uiState.value,
        )
    }

    private companion object {
        private const val NAME = "name"
        private const val MESSAGE = "message"
    }
}
