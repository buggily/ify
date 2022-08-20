package com.buggily.ify.ui.home

import com.buggily.ify.data.age.Age
import com.buggily.ify.data.gender.Gender
import com.buggily.ify.data.nationality.Nationality
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.roundToInt

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
            private val age: Age,
            private val numberFormat: NumberFormat,
        ) : AgeState() {

            val nameDisplay: String
                get() = age.name

            val ageDisplay: String
                get() = age.age.toString()

            val countDisplay: String
                get() = numberFormat.format(age.count)
        }

        data class Error(
            private val error: String,
        ) : AgeState() {

            val errorDisplay: String
                get() = error
        }
    }

    sealed class GenderState {

        object Default : GenderState()
        object Loading : GenderState()

        data class Success(
            private val gender: Gender,
            private val numberFormat: NumberFormat,
            private val locale: Locale,
        ) : GenderState() {

            val nameDisplay: String
                get() = gender.name

            val genderDisplay: String
                get() = gender.gender.toString().lowercase(locale)

            val percentageDisplay: String
                get() = percentage.toString()

            val countDisplay: String
                get() = numberFormat.format(gender.count)

            private val percentage: Int
                get() {
                    val percentage: Float = gender.probability * 100
                    return percentage.roundToInt()
                }
        }

        data class Error(
            private val error: String,
        ) : GenderState() {

            val errorDisplay: String
                get() = error
        }
    }

    sealed class NationalityState {

        object Default : NationalityState()
        object Loading : NationalityState()

        data class Success(
            private val nationality: Nationality,
        ) : NationalityState() {

            val nameDisplay: String
                get() = nationality.name

            val countriesDisplay: String?
                get() = nationality.countries.takeIf { it.isNotEmpty() }?.joinToString {
                    val locale = Locale(String(), it.country)
                    val percentage: Float = it.probability * 100
                    val percentageDisplay: Int = percentage.roundToInt()
                    "${locale.displayCountry} ($percentageDisplay%)"
                }
        }

        data class Error(
            private val error: String,
        ) : NationalityState() {

            val errorDisplay: String
                get() = error
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