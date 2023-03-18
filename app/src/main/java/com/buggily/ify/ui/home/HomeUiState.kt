package com.buggily.ify.ui.home

import com.buggily.ify.feature.age.AgeUiState
import com.buggily.ify.feature.gender.GenderUiState
import com.buggily.ify.feature.nationality.NationalityUiState

data class HomeUiState(
    val nameState: NameState,
    val ageState: AgeUiState,
    val genderState: GenderUiState,
    val nationalityState: NationalityUiState,
) {

    data class NameState(
        val name: String,
        val onChange: (String) -> Unit,
        val onClear: (() -> Unit)?,
    ) {

        companion object {
            val default: NameState
                get() = NameState(
                    name = String(),
                    onChange = {},
                    onClear = null,
                )
        }
    }

    companion object {
        val default: HomeUiState
            get() = HomeUiState(
                nameState = NameState.default,
                ageState = AgeUiState.Default,
                genderState = GenderUiState.Default,
                nationalityState = NationalityUiState.Default,
            )
    }
}
