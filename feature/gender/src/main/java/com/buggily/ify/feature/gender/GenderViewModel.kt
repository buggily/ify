package com.buggily.ify.feature.gender

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buggily.ify.core.domain.Format
import com.buggily.ify.core.model.DataResult
import com.buggily.ify.core.ui.NameableViewModel
import com.buggily.ify.data.gender.Gender
import com.buggily.ify.domain.gender.GetGenderByName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(
    private val getGenderByName: GetGenderByName,
    private val format: Format,
) : ViewModel(), NameableViewModel {

    private val _uiState: MutableStateFlow<GenderUiState> =
        MutableStateFlow(GenderUiState.Default)

    val uiState: StateFlow<GenderUiState> get() = _uiState

    override fun onNameChange(name: String) {
        val genderState: GenderUiState = if (name.isBlank()) {
            GenderUiState.Default
        } else {
            GenderUiState.Loading
        }

        setGenderState(genderState)
        if (genderState is GenderUiState.Default) return

        viewModelScope.launch {
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
    }

    private fun setGenderState(
        genderState: GenderUiState,
    ) = _uiState.update {
        genderState
    }
}
