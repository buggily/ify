package com.buggily.ify.feature.age

import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.buggily.ify.domain.age.AgeUi
import org.junit.Rule
import org.junit.Test
import com.buggily.ify.core.ui.R as CR

class AgeUiTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun ageScreenHasDefaultTextWhenAgeDefault() {
        rule.setContent {
            AgeScreen(AgeUiState.Default)
        }

        val age: String = rule.activity.getString(R.string.age_age)
        val enter: String = rule.activity.getString(CR.string.enter, age)

        rule.onNodeWithText(enter).assertIsDisplayed()
    }

    @Test
    fun ageScreenHasProgressIndicatorWhenAgeLoading() {
        rule.setContent {
            AgeScreen(AgeUiState.Loading)
        }

        rule.onNode(hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate)).assertIsDisplayed()
    }

    @Test
    fun ageScreenHasAgeTextWhenAgeResponse() {
        val age = AgeUi(
            nameText = "name",
            ageText = null,
            countText = null,
        )

        val uiState = AgeUiState.Response(
            age = age,
        )

        rule.setContent {
            AgeScreen(uiState)
        }

        rule.onNodeWithText(
            text = age.nameText,
            substring = true,
        ).assertIsDisplayed()
    }

    @Test
    fun ageScreenHasErrorMessageWhenAgeFailureRemoteApi() {
        val uiState = AgeUiState.Failure.Remote.Api(
            message = "message",
        )

        rule.setContent {
            AgeScreen(uiState)
        }

        rule.onNodeWithText(uiState.message).assertIsDisplayed()
    }

    @Test
    fun ageScreenHasNetworkErrorMessageOnAgeFailureRemoteNetwork() {
        rule.setContent {
            AgeScreen(AgeUiState.Failure.Remote.Network)
        }

        val networkError: String = rule.activity.getString(CR.string.error_network)
        rule.onNodeWithText(networkError).assertIsDisplayed()
    }

    @Test
    fun ageScreenHasUnknownErrorMessageOnAgeFailureElse() {
        rule.setContent {
            AgeScreen(AgeUiState.Failure.Else)
        }

        val unknownError: String = rule.activity.getString(CR.string.error_unknown)
        rule.onNodeWithText(unknownError).assertIsDisplayed()
    }
}
