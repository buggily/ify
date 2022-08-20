package com.buggily.ify.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buggily.ify.data.rest.Rest
import com.buggily.ify.data.rest.age.AgeRest
import com.buggily.ify.data.rest.gender.GenderRest
import com.buggily.ify.data.rest.nationality.NationalityRest
import com.buggily.ify.di.DebounceQualifier
import com.buggily.ify.di.NameQualifier
import com.buggily.ify.domain.use.age.GetAge
import com.buggily.ify.domain.use.gender.GetGender
import com.buggily.ify.domain.use.nationality.GetNationality
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @NameQualifier name: String,
    @DebounceQualifier debounce: Long,
    private val getAge: GetAge,
    private val getGender: GetGender,
    private val getNationality: GetNationality,
    private val numberFormat: NumberFormat,
    private val locale: Locale,
) : ViewModel() {

    private val _state: MutableStateFlow<HomeState>
    val state: StateFlow<HomeState> get() = _state

    init {
        HomeState.default.copy(
            nameState = HomeState.NameState.default.copy(
                name = name,
                onNameChange = ::onNameChange,
                onNameClear = ::onNameClear,
            ),
            ageState = HomeState.AgeState.Loading,
        ).let { _state = MutableStateFlow(it) }

        viewModelScope.launch {
            state.map {
                it.nameState.name
            }.debounce(debounce).stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = name,
            ).collectLatest {
                launch { setAge(it) }
                launch { setGender(it) }
                launch { setNationality(it) }
            }
        }
    }

    private fun onNameChange(name: String) = setNameOfNameState(
        name = name,
    )

    private fun onNameClear() = onNameChange(
        name = HomeState.NameState.default.name,
    )

    private fun setNameOfNameState(name: String) = state.value.let {
        val nameState: HomeState.NameState = it.nameState.copy(
            name = name,
        )

        setNameState(nameState)
    }

    private suspend fun setAge(name: String) {
        if (name == HomeState.NameState.default.name) {
            setAgeState(HomeState.AgeState.Default)
            return
        } else {
            setAgeState(HomeState.AgeState.Loading)
        }

        when (val age: AgeRest = getAge(name)) {
            is Rest.Success -> HomeState.AgeState.Success(
                age = age.body,
                numberFormat = numberFormat,
            )
            is Rest.Error.Api -> HomeState.AgeState.Error(
                error = age.errorBody.error,
            )
            else -> HomeState.AgeState.Default
        }.let { setAgeState(it) }
    }

    private suspend fun setGender(name: String) {
        if (name == HomeState.NameState.default.name) {
            setGenderState(HomeState.GenderState.Default)
            return
        } else {
            setGenderState(HomeState.GenderState.Loading)
        }

        when (val gender: GenderRest = getGender(name)) {
            is Rest.Success -> HomeState.GenderState.Success(
                gender = gender.body,
                numberFormat = numberFormat,
                locale = locale,
            )
            is Rest.Error.Api -> HomeState.GenderState.Error(
                error = gender.errorBody.error,
            )
            else -> HomeState.GenderState.Default
        }.let { setGenderState(it) }
    }

    private suspend fun setNationality(name: String) {
        if (name == HomeState.NameState.default.name) {
            setNationalityState(HomeState.NationalityState.Default)
            return
        } else {
            setNationalityState(HomeState.NationalityState.Loading)
        }

        when (val nationality: NationalityRest = getNationality(name)) {
            is Rest.Success -> HomeState.NationalityState.Success(
                nationality = nationality.body,
            )
            is Rest.Error.Api -> HomeState.NationalityState.Error(
                error = nationality.errorBody.error,
            )
            else -> HomeState.NationalityState.Default
        }.let { setNationalityState(it) }
    }

    private fun setNameState(nameState: HomeState.NameState) = _state.update {
        it.copy(nameState = nameState)
    }

    private fun setAgeState(ageState: HomeState.AgeState) = _state.update {
        it.copy(ageState = ageState)
    }

    private fun setGenderState(genderState: HomeState.GenderState) = _state.update {
        it.copy(genderState = genderState)
    }

    private fun setNationalityState(nationalityState: HomeState.NationalityState) = _state.update {
        it.copy(nationalityState = nationalityState)
    }
}
