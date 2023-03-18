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
import com.buggily.ify.feature.age.AgeUiState
import com.buggily.ify.feature.gender.GenderUiState
import com.buggily.ify.feature.nationality.NationalityUiState
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

    private val _uiState: MutableStateFlow<HomeUiState>
    val uiState: StateFlow<HomeUiState> get() = _uiState

    init {
        HomeUiState.default.copy(
            nameState = HomeUiState.NameState.default.copy(
                name = "Adam",
                onChange = ::onNameChange,
                onClear = ::onNameClear,
            ),
        ).let { _uiState = MutableStateFlow(it) }

        val nameState: Flow<HomeUiState.NameState> = uiState.map { it.nameState }
        val name: Flow<String> = nameState.map { it.name }

        viewModelScope.launch {
            name.debounce(1000).map {
                it.takeUnless { it.isBlank() } ?: HomeUiState.NameState.default.name
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = uiState.value.nameState.name,
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
        name = HomeUiState.NameState.default.name,
    )

    private fun setNameOfNameState(name: String) = uiState.value.let {
        val onClear: (() -> Unit)? = if (name.isNotEmpty()) ::onNameClear else null

        val nameState: HomeUiState.NameState = it.nameState.copy(
            name = name,
            onClear = onClear,
        )

        setNameState(nameState)
    }

    private suspend fun setAge(name: String) {
        val ageState: AgeUiState = if (name == HomeUiState.NameState.default.name) {
            AgeUiState.Default
        } else {
            AgeUiState.Loading
        }

        setAgeState(ageState)
        if (ageState is AgeUiState.Default) return

        when (val age: Rest<Age, Age.Error> = getAge(name)) {
            is Rest.Success -> AgeUiState.Success(
                age = age.body,
                formatNumber = format.formatNumber,
            )
            is Rest.Error.Api -> AgeUiState.Error.Api(
                error = age.errorBody.error,
            )
            is Rest.Error.Network -> AgeUiState.Error.Network
            is Rest.Error.Else -> AgeUiState.Error.Else
        }.let { setAgeState(it) }
    }

    private suspend fun setGender(name: String) {
        val genderState: GenderUiState = if (name == HomeUiState.NameState.default.name) {
            GenderUiState.Default
        } else {
            GenderUiState.Loading
        }

        setGenderState(genderState)
        if (genderState is GenderUiState.Default) return

        when (val gender: Rest<Gender, Gender.Error> = getGender(name)) {
            is Rest.Success -> GenderUiState.Success(
                gender = gender.body,
                format = format,
            )
            is Rest.Error.Api -> GenderUiState.Error.Api(
                error = gender.errorBody.error,
            )
            is Rest.Error.Network -> GenderUiState.Error.Network
            is Rest.Error.Else -> GenderUiState.Error.Else
        }.let { setGenderState(it) }
    }

    private suspend fun setNationality(name: String) {
        val nationalityState: NationalityUiState = if (name == HomeUiState.NameState.default.name) {
            NationalityUiState.Default
        } else {
            NationalityUiState.Loading
        }

        setNationalityState(nationalityState)
        if (nationalityState is NationalityUiState.Default) return

        when (val nationality: Rest<Nationality, Nationality.Error> = getNationality(name)) {
            is Rest.Success -> NationalityUiState.Success(
                nationality = nationality.body,
                format = format,
            )
            is Rest.Error.Api -> NationalityUiState.Error.Api(
                error = nationality.errorBody.error,
            )
            is Rest.Error.Network -> NationalityUiState.Error.Network
            is Rest.Error.Else -> NationalityUiState.Error.Else
            else -> NationalityUiState.Default
        }.let { setNationalityState(it) }
    }

    private fun setNameState(nameState: HomeUiState.NameState) = _uiState.update {
        it.copy(nameState = nameState)
    }

    private fun setAgeState(ageState: AgeUiState) = _uiState.update {
        it.copy(ageState = ageState)
    }

    private fun setGenderState(genderState: GenderUiState) = _uiState.update {
        it.copy(genderState = genderState)
    }

    private fun setNationalityState(nationalityState: NationalityUiState) = _uiState.update {
        it.copy(nationalityState = nationalityState)
    }
}
