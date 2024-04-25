package com.buggily.ify.feature.age

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buggily.ify.core.model.DataResult
import com.buggily.ify.core.ui.NameableViewModel
import com.buggily.ify.domain.age.AgeUi
import com.buggily.ify.domain.age.GetAgeByName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(
    private val getAgeByName: GetAgeByName,
) : ViewModel(), NameableViewModel {

    private val _uiState: MutableStateFlow<AgeUiState> =
        MutableStateFlow(AgeUiState.Default)

    val uiState: StateFlow<AgeUiState>
        get() = _uiState

    override fun onNameChange(name: String) {
        val ageState: AgeUiState = if (name.isBlank()) {
            AgeUiState.Default
        } else {
            AgeUiState.Loading
        }

        setAgeState(ageState)
        if (ageState is AgeUiState.Default) return

        viewModelScope.launch {
            when (val result: DataResult<AgeUi> = getAgeByName(name)) {
                is DataResult.Response -> AgeUiState.Response(
                    age = result.value,
                )

                is DataResult.Failure.Remote.Api -> AgeUiState.Failure.Remote.Api(
                    message = result.message,
                )

                is DataResult.Failure.Remote.Network -> AgeUiState.Failure.Remote.Network
                is DataResult.Failure.Remote.Else, DataResult.Failure.Local -> AgeUiState.Failure.Else
            }.let { setAgeState(it) }
        }
    }

    private fun setAgeState(
        ageState: AgeUiState,
    ) = _uiState.update {
        ageState
    }
}
