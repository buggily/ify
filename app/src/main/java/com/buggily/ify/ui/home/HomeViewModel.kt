package com.buggily.ify.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buggily.ify.core.domain.Format
import com.buggily.ify.core.model.DataResult
import com.buggily.ify.data.age.Age
import com.buggily.ify.data.gender.Gender
import com.buggily.ify.data.nationality.Nationality
import com.buggily.ify.domain.age.GetAgeByName
import com.buggily.ify.domain.gender.GetGenderByName
import com.buggily.ify.domain.nationality.GetNationalityByName
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
    private val getAgeByName: GetAgeByName,
    private val getGenderByName: GetGenderByName,
    private val getNationalityByName: GetNationalityByName,
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

        when (val result: DataResult<Age> = getAgeByName(name)) {
            is DataResult.Response -> AgeUiState.Response(
                age = result.value,
                formatNumber = format.formatNumber,
            )
            is DataResult.Failure.Remote.Api -> AgeUiState.Failure.Remote.Api(
                message = result.message,
            )
            is DataResult.Failure.Remote.Network -> AgeUiState.Failure.Remote.Network
            is DataResult.Failure.Remote.Else,
            DataResult.Failure.Local -> AgeUiState.Failure.Else
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

        when (val result: DataResult<Gender> = getGenderByName(name)) {
            is DataResult.Response -> GenderUiState.Response(
                gender = result.value,
                format = format,
            )
            is DataResult.Failure.Remote.Api -> GenderUiState.Failure.Remote.Api(
                message = result.message,
            )
            is DataResult.Failure.Remote.Network -> GenderUiState.Failure.Remote.Network
            is DataResult.Failure.Remote.Else,
            DataResult.Failure.Local -> GenderUiState.Failure.Else
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

        when (val result: DataResult<Nationality> = getNationalityByName(name)) {
            is DataResult.Response -> NationalityUiState.Response(
                nationality = result.value,
                format = format,
            )
            is DataResult.Failure.Remote.Api -> NationalityUiState.Failure.Remote.Api(
                message = result.message,
            )
            is DataResult.Failure.Remote.Network -> NationalityUiState.Failure.Remote.Network
            is DataResult.Failure.Remote.Else,
            DataResult.Failure.Local -> NationalityUiState.Failure.Else
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
