package com.buggily.ify.ui.home

import com.buggily.ify.feature.age.AgeState
import com.buggily.ify.feature.gender.GenderState
import com.buggily.ify.feature.nationality.NationalityState

data class HomeState(
    val nameState: NameState,
    val ageState: AgeState,
    val genderState: GenderState,
    val nationalityState: NationalityState,
) {

    data class NameState(
        val name: String,
        val onNameChange: (String) -> Unit,
        val onNameClear: (() -> Unit)?,
    ) {

        companion object {
            val default: NameState
                get() = NameState(
                    name = String(),
                    onNameChange = {},
                    onNameClear = null,
                )
        }
    }

    companion object {
        val default: HomeState
            get() = HomeState(
                nameState = NameState.default,
                ageState = AgeState.Default,
                genderState = GenderState.Default,
                nationalityState = NationalityState.Default,
            )
    }
}
