package com.buggily.ify.feature.nationality

import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.buggily.ify.domain.nationality.NationalityUi
import org.junit.Rule
import org.junit.Test
import com.buggily.ify.core.ui.R as CR

class NationalityUiTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun nationalityScreenHasDefaultTextWhenNationalityDefault() {
        rule.setContent {
            NationalityScreen(NationalityUiState.Default)
        }

        val nationality: String = rule.activity.getString(R.string.nationality_nationality)
        val enter: String = rule.activity.getString(CR.string.enter, nationality)

        rule.onNodeWithText(enter).assertIsDisplayed()
    }

    @Test
    fun nationalityScreenHasProgressIndicatorWhenNationalityLoading() {
        rule.setContent {
            NationalityScreen(NationalityUiState.Loading)
        }

        rule.onNode(hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate)).assertIsDisplayed()
    }

    @Test
    fun nationalityScreenHasNationalityTextWhenNationalityResponse() {
        val nationality = NationalityUi(
            nameText = "name",
            countText = null,
            nationsText = null,
        )

        val uiState = NationalityUiState.Response(
            nationality = nationality,
        )

        rule.setContent {
            NationalityScreen(uiState)
        }

        rule.onNodeWithText(
            text = nationality.nameText,
            substring = true,
        ).assertIsDisplayed()
    }

    @Test
    fun nationalityScreenHasErrorMessageWhenNationalityFailureRemoteApi() {
        val uiState = NationalityUiState.Failure.Remote.Api(
            message = "message",
        )

        rule.setContent {
            NationalityScreen(uiState)
        }

        rule.onNodeWithText(uiState.message).assertIsDisplayed()
    }

    @Test
    fun nationalityScreenHasNetworkErrorMessageOnNationalityFailureRemoteNetwork() {
        rule.setContent {
            NationalityScreen(NationalityUiState.Failure.Remote.Network)
        }

        val networkError: String = rule.activity.getString(CR.string.error_network)
        rule.onNodeWithText(networkError).assertIsDisplayed()
    }

    @Test
    fun nationalityScreenHasUnknownErrorMessageOnNationalityFailureElse() {
        rule.setContent {
            NationalityScreen(NationalityUiState.Failure.Else)
        }

        val unknownError: String = rule.activity.getString(CR.string.error_unknown)
        rule.onNodeWithText(unknownError).assertIsDisplayed()
    }
}
