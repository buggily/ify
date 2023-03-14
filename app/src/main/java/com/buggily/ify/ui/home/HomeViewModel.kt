package com.buggily.ify.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buggily.ify.core.domain.use.Format
import com.buggily.ify.core.model.Rest
import com.buggily.ify.core.model.age.Age
import com.buggily.ify.core.model.gender.Gender
import com.buggily.ify.core.model.nationality.Nationality
import com.buggily.ify.domain.age.use.GetAge
import com.buggily.ify.domain.gender.use.GetGender
import com.buggily.ify.domain.nationality.use.GetNationality
import com.buggily.ify.feature.age.AgeState
import com.buggily.ify.feature.gender.GenderState
import com.buggily.ify.feature.nationality.NationalityState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@OptIn(FlowPreview::class)
class HomeViewModel @Inject constructor(
    private val getAge: GetAge,
    private val getGender: GetGender,
    private val getNationality: GetNationality,
    private val format: Format,
) : ViewModel() {

    private val _state: MutableStateFlow<HomeState>
    val state: StateFlow<HomeState> get() = _state

    init {
        HomeState.default.copy(
            nameState = HomeState.NameState.default.copy(
                name = "Adam",
                onChange = ::onNameChange,
                onClear = ::onNameClear,
            ),
        ).let { _state = MutableStateFlow(it) }

        val nameState: Flow<HomeState.NameState> = state.map { it.nameState }
        val name: Flow<String> = nameState.map { it.name }

        viewModelScope.launch {
            name.debounce(1000).map {
                it.takeUnless { it.isBlank() } ?: HomeState.NameState.default.name
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = state.value.nameState.name,
            ).collectLatest {
                launch { setAge(it) }
                launch { setGender(it) }
                launch { setNationality(it) }
            }
        }
    }

    private fun onNameChange(name: String): Unit = setNameOfNameState(
        name = name,
    )

    private fun onNameClear(): Unit = onNameChange(
        name = HomeState.NameState.default.name,
    )

    private fun setNameOfNameState(name: String) = state.value.let {
        val onClear: (() -> Unit)? = if (name.isNotEmpty()) ::onNameClear else null

        val nameState: HomeState.NameState = it.nameState.copy(
            name = name,
            onClear = onClear,
        )

        setNameState(nameState)
    }

    private suspend fun setAge(name: String) {
        val ageState: AgeState = if (name == HomeState.NameState.default.name) {
            AgeState.Default
        } else {
            AgeState.Loading
        }

        setAgeState(ageState)
        if (ageState is AgeState.Default) return

        when (val age: Rest<Age, Age.Error> = getAge(name)) {
            is Rest.Success -> AgeState.Success(
                age = age.body,
                formatNumber = format.formatNumber,
            )
            is Rest.Error.Api -> AgeState.Error(
                error = age.errorBody.error,
            )
            else -> AgeState.Default
        }.let { setAgeState(it) }
    }

    private suspend fun setGender(name: String) {
        val genderState: GenderState = if (name == HomeState.NameState.default.name) {
            GenderState.Default
        } else {
            GenderState.Loading
        }

        setGenderState(genderState)
        if (genderState is GenderState.Default) return

        when (val gender: Rest<Gender, Gender.Error> = getGender(name)) {
            is Rest.Success -> GenderState.Success(
                gender = gender.body,
                format = format,
            )
            is Rest.Error.Api -> GenderState.Error(
                error = gender.errorBody.error,
            )
            else -> GenderState.Default
        }.let { setGenderState(it) }
    }

    private suspend fun setNationality(name: String) {
        val nationalityState: NationalityState = if (name == HomeState.NameState.default.name) {
            NationalityState.Default
        } else {
            NationalityState.Loading
        }

        setNationalityState(nationalityState)
        if (nationalityState is NationalityState.Default) return

        when (val nationality: Rest<Nationality, Nationality.Error> = getNationality(name)) {
            is Rest.Success -> NationalityState.Success(
                nationality = nationality.body,
                format = format,
            )
            is Rest.Error.Api -> NationalityState.Error(
                error = nationality.errorBody.error,
            )
            else -> NationalityState.Default
        }.let { setNationalityState(it) }
    }

    private fun setNameState(nameState: HomeState.NameState) = _state.update {
        it.copy(nameState = nameState)
    }

    private fun setAgeState(ageState: AgeState) = _state.update {
        it.copy(ageState = ageState)
    }

    private fun setGenderState(genderState: GenderState) = _state.update {
        it.copy(genderState = genderState)
    }

    private fun setNationalityState(nationalityState: NationalityState) = _state.update {
        it.copy(nationalityState = nationalityState)
    }
}
