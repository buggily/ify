package com.buggily.ify.feature.nationality

import com.buggily.ify.core.model.DataResult
import com.buggily.ify.core.test.CoroutineTestRule
import com.buggily.ify.domain.nationality.GetNationalityByName
import com.buggily.ify.domain.nationality.NationalityUi
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NationalityViewModelTest {

    private val getNationalityByName: GetNationalityByName = mockk()
    private lateinit var viewModel: NationalityViewModel

    @get:Rule
    val rule = CoroutineTestRule

    @Before
    fun before() {
        clearAllMocks()
        viewModel = NationalityViewModel(getNationalityByName)
    }

    @Test
    fun `ui state should be default on init`() = runTest {
        Assert.assertEquals(
            NationalityUiState.Default,
            viewModel.uiState.value,
        )
    }

    @Test
    fun `ui state should be default on name change with blank name`() = runTest {
        viewModel.onNameChange(String())

        Assert.assertEquals(
            NationalityUiState.Default,
            viewModel.uiState.value,
        )
    }

    @Test
    fun `ui state should be response on name change with response`() = runTest {
        val ui = NationalityUi(
            nameText = NAME,
            countText = String(),
            nationsText = String(),
        )

        coEvery { getNationalityByName(NAME) } returns DataResult.Response.Remote(ui)
        viewModel.onNameChange(NAME)

        Assert.assertEquals(
            NationalityUiState.Response(ui),
            viewModel.uiState.value,
        )
    }

    @Test
    fun `ui state should be remote api failure on name change with remote api failure`() = runTest {
        coEvery { getNationalityByName(NAME) } returns DataResult.Failure.Remote.Api(MESSAGE)
        viewModel.onNameChange(NAME)

        Assert.assertEquals(
            NationalityUiState.Failure.Remote.Api(MESSAGE),
            viewModel.uiState.value,
        )
    }

    @Test
    fun `ui state should be remote network failure on name change with remote network failure`() =
        runTest {
            coEvery { getNationalityByName(NAME) } returns DataResult.Failure.Remote.Network(MESSAGE)
            viewModel.onNameChange(NAME)

            Assert.assertEquals(
                NationalityUiState.Failure.Remote.Network,
                viewModel.uiState.value,
            )
        }

    @Test
    fun `ui state should be else failure on name change with else failure`() = runTest {
        coEvery { getNationalityByName(NAME) } returns DataResult.Failure.Remote.Else(MESSAGE)
        viewModel.onNameChange(NAME)

        Assert.assertEquals(
            NationalityUiState.Failure.Else,
            viewModel.uiState.value,
        )
    }

    private companion object {
        private const val NAME = "name"
        private const val MESSAGE = "message"
    }
}