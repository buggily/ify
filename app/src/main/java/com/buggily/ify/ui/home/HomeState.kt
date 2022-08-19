package com.buggily.ify.ui.home

import com.buggily.ify.data.age.Age
import com.buggily.ify.data.gender.Gender
import com.buggily.ify.data.nationality.Nationality

data class HomeState(
    val nameState: NameState,
    val ageState: AgeState,
    val genderState: GenderState,
    val nationalityState: NationalityState,
) {

    data class NameState(
        val name: String,
        val onNameChange: (String) -> Unit,
        val onNameClear: () -> Unit,
    ) {

        companion object {
            val default: NameState
                get() = NameState(
                    name = String(),
                    onNameChange = {},
                    onNameClear = {},
                )
        }
    }

    sealed class AgeState {

        object Default : AgeState()
        object Loading : AgeState()

        data class Success(
            val age: Age,
        ) : AgeState()

        data class Error(
            val error: String,
        ) : AgeState()
    }

    sealed class GenderState {

        object Default : GenderState()
        object Loading : GenderState()

        data class Success(
            val gender: Gender,
        ) : GenderState()

        data class Error(
            val error: String,
        ) : GenderState()
    }

    sealed class NationalityState {

        object Default : NationalityState()
        object Loading : NationalityState()

        data class Success(
            val nationality: Nationality,
        ) : NationalityState()

        data class Error(
            val error: String,
        ) : NationalityState()
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