package com.buggily.ify.feature.nationality

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buggily.ify.core.model.DataResult
import com.buggily.ify.core.ui.NameableViewModel
import com.buggily.ify.domain.nationality.GetNationalityByName
import com.buggily.ify.domain.nationality.NationalityUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NationalityViewModel @Inject constructor(
    private val getNationalityByName: GetNationalityByName,
) : ViewModel(), NameableViewModel {

    private val _uiState: MutableStateFlow<NationalityUiState> =
        MutableStateFlow(NationalityUiState.Default)

    val uiState: StateFlow<NationalityUiState>
        get() = _uiState

    override fun onNameChange(name: String) {
        val nationalityState: NationalityUiState = if (name.isBlank()) {
            NationalityUiState.Default
        } else {
            NationalityUiState.Loading
        }

        setNationalityState(nationalityState)
        if (nationalityState is NationalityUiState.Default) return

        viewModelScope.launch {
            when (val result: DataResult<NationalityUi> = getNationalityByName(name)) {
                is DataResult.Response -> NationalityUiState.Response(
                    nationality = result.value,
                )

                is DataResult.Failure.Remote.Api -> NationalityUiState.Failure.Remote.Api(
                    message = result.message,
                )

                is DataResult.Failure.Remote.Network -> NationalityUiState.Failure.Remote.Network
                is DataResult.Failure.Remote.Else, DataResult.Failure.Local -> NationalityUiState.Failure.Else
            }.let { setNationalityState(it) }
        }
    }

    private fun setNationalityState(
        nationalityState: NationalityUiState,
    ) = _uiState.update {
        nationalityState
    }
}
