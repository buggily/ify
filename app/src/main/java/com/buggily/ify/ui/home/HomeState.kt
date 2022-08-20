package com.buggily.ify.ui.home

import com.buggily.ify.data.age.Age
import com.buggily.ify.data.gender.Gender
import com.buggily.ify.data.nationality.Nationality
import com.buggily.ify.use.FormatNumber
import com.buggily.ify.use.Lowercase
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
            private val formatNumber: FormatNumber,
        ) : AgeState() {

            val nameText: String
                get() = age.name

            val ageText: String
                get() = age.age.toString()

            val countText: String
                get() = formatNumber(age.count)
        }

        data class Error(
            private val error: String,
        ) : AgeState() {

            val errorText: String
                get() = error
        }
    }

    sealed class GenderState {

        object Default : GenderState()
        object Loading : GenderState()

        data class Success(
            private val gender: Gender,
            private val formatNumber: FormatNumber,
            private val lowercase: Lowercase,
        ) : GenderState() {

            val nameText: String
                get() = gender.name

            val genderText: String
                get() = lowercase(gender.gender.toString())

            val percentageText: String
                get() = percentage.toString()

            val countText: String
                get() = formatNumber(gender.count)

            private val percentage: Int
                get() {
                    val percentage: Float = gender.probability * 100
                    return percentage.roundToInt()
                }
        }

        data class Error(
            private val error: String,
        ) : GenderState() {

            val errorText: String
                get() = error
        }
    }

    sealed class NationalityState {

        object Default : NationalityState()
        object Loading : NationalityState()

        data class Success(
            private val nationality: Nationality,
        ) : NationalityState() {

            val nameText: String
                get() = nationality.name

            val countriesText: String?
                get() = nationality.countries.takeIf { it.isNotEmpty() }?.joinToString {
                    val locale = Locale(String(), it.country)
                    val percentage: Float = it.probability * 100
                    val percentageText: Int = percentage.roundToInt()
                    "${locale.displayCountry} ($percentageText%)"
                }
        }

        data class Error(
            private val error: String,
        ) : NationalityState() {

            val errorText: String
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