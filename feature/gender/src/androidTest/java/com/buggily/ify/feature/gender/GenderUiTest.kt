package com.buggily.ify.feature.gender

import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.buggily.ify.domain.gender.GenderUi
import org.junit.Rule
import org.junit.Test
import com.buggily.ify.core.ui.R as CR

class GenderUiTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun genderScreenHasDefaultTextWhenGenderDefault() {
        rule.setContent {
            GenderScreen(GenderUiState.Default)
        }

        val gender: String = rule.activity.getString(R.string.gender_gender)
        val enter: String = rule.activity.getString(CR.string.enter, gender)

        rule.onNodeWithText(enter).assertIsDisplayed()
    }

    @Test
    fun genderScreenHasProgressIndicatorWhenGenderLoading() {
        rule.setContent {
            GenderScreen(GenderUiState.Loading)
        }

        rule.onNode(hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate)).assertIsDisplayed()
    }

    @Test
    fun genderScreenHasGenderTextWhenGenderResponse() {
        val gender = GenderUi(
            nameText = "name",
            gender = null,
            probabilityText = null,
            countText = null,
        )

        val uiState = GenderUiState.Response(
            gender = gender,
        )

        rule.setContent {
            GenderScreen(uiState)
        }

        rule.onNodeWithText(
            text = gender.nameText,
            substring = true,
        ).assertIsDisplayed()
    }

    @Test
    fun genderScreenHasErrorMessageWhenGenderFailureRemoteApi() {
        val uiState = GenderUiState.Failure.Remote.Api(
            message = "message",
        )

        rule.setContent {
            GenderScreen(uiState)
        }

        rule.onNodeWithText(uiState.message).assertIsDisplayed()
    }

    @Test
    fun genderScreenHasNetworkErrorMessageOnGenderFailureRemoteNetwork() {
        rule.setContent {
            GenderScreen(GenderUiState.Failure.Remote.Network)
        }

        val networkError: String = rule.activity.getString(CR.string.error_network)
        rule.onNodeWithText(networkError).assertIsDisplayed()
    }

    @Test
    fun genderScreenHasUnknownErrorMessageOnGenderFailureElse() {
        rule.setContent {
            GenderScreen(GenderUiState.Failure.Else)
        }

        val unknownError: String = rule.activity.getString(CR.string.error_unknown)
        rule.onNodeWithText(unknownError).assertIsDisplayed()
    }
}
