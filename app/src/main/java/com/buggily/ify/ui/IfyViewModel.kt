package com.buggily.ify.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buggily.ify.core.domain.FormatCapitalize
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class IfyViewModel @Inject constructor(
    private val formatCapitalize: FormatCapitalize,
) : ViewModel() {

    private val _uiState: MutableStateFlow<IfyUiState>
    val uiState: StateFlow<IfyUiState> get() = _uiState

    val name: StateFlow<String>

    init {
        IfyUiState(
            nameState = IfyUiState.NameState(
                value = DEFAULT_NAME,
                onValueChange = ::onNameChange,
                onValueClear = ::onNameClear,
            ),
        ).let { _uiState = MutableStateFlow(it) }

        name = uiState.map { formatCapitalize(it.nameState.value) }.debounce(1000).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = uiState.value.nameState.value,
        )
    }

    private fun onNameChange(name: String) = setNameOfNameState(
        name = name,
    )

    private fun onNameClear(): Unit = onNameChange(
        name = String(),
    )

    private fun setNameOfNameState(name: String) = uiState.value.let {
        val onValueClear: (() -> Unit)? = if (name.isNotEmpty()) ::onNameClear else null

        val nameState: IfyUiState.NameState = it.nameState.copy(
            value = name,
            onValueClear = onValueClear,
        )

        setNameState(nameState)
    }

    private fun setNameState(nameState: IfyUiState.NameState) = _uiState.update {
        it.copy(nameState = nameState)
    }

    companion object {
        const val DEFAULT_NAME = "Adam"
    }
}
